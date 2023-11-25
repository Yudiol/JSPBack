package com.yudiol.JobSearchPlatformBack.util;

import com.yudiol.JobSearchPlatformBack.model.Company;
import com.yudiol.JobSearchPlatformBack.model.Institution;
import com.yudiol.JobSearchPlatformBack.model.Resume;
import com.yudiol.JobSearchPlatformBack.service.GeneratePdfService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class GeneratePdf implements GeneratePdfService {
    private float FONT_SIZE = 0;
    private float LEADING = -1.5f * FONT_SIZE;
    private float START_Y = 0f;
    private float START_X = 20f;
    private PDRectangle mediaBox;
    private static char c = '\u2022';
    private PDDocument document = new PDDocument();
    private PDPageContentStream contentStream;
    private final File file = new File("arial.ttf");
    private PDFont FONT;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/yy");

    public void downloadPdf(Resume resume, String userId) throws IOException {

        String name = resume.getContact().getName() + " " + resume.getContact().getSurname();
        String secondLine = resume.getContact().getEmail() + " | " + resume.getContact().getPhone() + " | " + resume.getContact().getLinkedin() + " | " + resume.getContact().getCountry();
        String summary = resume.getSummary().getSummary();
        String sillsList = String.join(" - ", resume.getSkill().getSkills());

        createPDPageContentStream();

        FONT_SIZE = 24;
        printCenterTitle(name);

        FONT_SIZE = 14;
        printCenterTitle(secondLine);

        START_Y -= 20;
        drawLine(20, START_Y);

        FONT_SIZE = 14;
        START_Y -= 20;
        printSummary(summary);

        FONT_SIZE = 18;
        printLeftTitle("НАВЫКИ");

        START_Y -= 10;
        drawLine(20, START_Y);

        FONT_SIZE = 14;
        START_Y -= 20;
        printParagraph(sillsList);

        FONT_SIZE = 18;
        printLeftTitle("ОПЫТ РАБОТЫ");

        START_Y -= 10;
        drawLine(20, START_Y);

        START_Y -= 20;
        FONT_SIZE = 12;
        printExperience(resume);

        FONT_SIZE = 18;
        printLeftTitle("ОБРАЗОВАНИЕ");

        START_Y -= 10;
        drawLine(20, START_Y);

        START_Y -= 20;
        FONT_SIZE = 12;
        printEducation(resume);

        contentStream.close();

        document.save("resumes/resume-" + userId + ".pdf");
        document.close();

    }

    private PDPageContentStream createPDPageContentStream() throws IOException {
        FONT = PDType0Font.load(document, file);
        if (contentStream != null) {
            contentStream.close();
        }
        PDPage pdPage = new PDPage();
        document.addPage(pdPage);
        contentStream = new PDPageContentStream(document, pdPage);
        mediaBox = pdPage.getMediaBox();
        START_Y = mediaBox.getHeight();
        return contentStream;
    }

    private void printCenterTitle(String str) throws IOException {
        float width = FONT.getStringWidth(str) / 1000 * FONT_SIZE;
        float centerX = (PDRectangle.A4.getWidth() - width) / 2;
        LEADING = -1.5f * FONT_SIZE;
        START_Y += LEADING;
        contentStream.beginText();
        contentStream.setFont(FONT, FONT_SIZE);
        contentStream.newLineAtOffset(centerX, START_Y);
        contentStream.showText(str);
        contentStream.endText();
    }

    private void printLeftTitle(String str) throws IOException {
        LEADING = -1.5f * FONT_SIZE;
        START_Y += LEADING;
        print(str);
    }

    private void print(String str) throws IOException {
        contentStream.beginText();
        contentStream.setFont(FONT, FONT_SIZE);
        contentStream.newLineAtOffset(START_X, START_Y);
        contentStream.showText(str);
        contentStream.endText();
    }

    private void printParagraph(String str) throws IOException {
        float width = mediaBox.getWidth() - 2 * START_X;
        LEADING = -1.5f * FONT_SIZE;
        addParagraph(width, START_X, START_Y, str, true);
    }

    private void printSummary(String str) throws IOException {
        float width = mediaBox.getWidth() - 2 * START_X;
        LEADING = -1.5f * FONT_SIZE;
        List<String> list = Arrays.stream(str.split("\n")).toList();
        for (String paragraph : list) {
            addParagraph(width, START_X, START_Y, paragraph, true);
        }
    }

    private void drawLine(float startX, float startY) throws IOException {
        float endX = mediaBox.getWidth() - startX;
        contentStream.moveTo(startX, startY);
        contentStream.lineTo(endX, startY);
        contentStream.stroke();
    }

    private void printExperience(Resume resume) throws IOException {

        List<Company> companyList = resume.getExperience().getCompanies();
        LEADING = -1.5f * FONT_SIZE;
        float width = mediaBox.getWidth() - 2 * START_X;
        for (Company company : companyList) {
            String startDate = company.getStartDate().format(dateTimeFormatter);
            String endDate = company.getStartDate().isBefore(LocalDate.now()) ? company.getEndDate().format(dateTimeFormatter) : " наст. время ";
            String companyName = company.getName();
            String position = company.getPosition();
            List<String> descriptionsList = Arrays.stream(company.getDescription().split("\n")).toList();

            String firstString = startDate + " - " + endDate + "   " + companyName + " - " + position;

            contentStream.setFont(FONT, FONT_SIZE);
            addParagraph(width, START_X, START_Y, firstString, true);

            for (String description : descriptionsList) {
                if (description == null || description.equals("")) {
                    continue;
                }
                print(String.valueOf(c));
                addParagraph(width - 40, START_X + 40, START_Y, description, true);
            }
        }
    }

    private void printEducation(Resume resume) throws IOException {

        List<Institution> companyList = resume.getEducation().getInstitutions();
        LEADING = -1.5f * FONT_SIZE;
        float width = mediaBox.getWidth() - 2 * START_X;
        for (Institution Institution : companyList) {
            String startDate = Institution.getStartDate().format(dateTimeFormatter);
            String endDate = Institution.getStartDate().isBefore(LocalDate.now()) ? Institution.getEndDate().format(dateTimeFormatter) : " наст. время ";
            String companyName = Institution.getName();
            String faculty = Institution.getPosition();
            List<String> descriptionsList = Arrays.stream(Institution.getDescription().split("\n")).toList();

            String firstString = startDate + " - " + endDate + "   " + companyName;

            contentStream.setFont(FONT, FONT_SIZE);
            addParagraph(width, START_X, START_Y, firstString, true);
            print("Факультет : " + faculty);
            START_Y += LEADING;
            for (String description : descriptionsList) {
                if (description == null || description.equals("")) {
                    continue;
                }
                StringBuilder stringBuilder = new StringBuilder(description);
                stringBuilder.insert(0, "        ");
                addParagraph(width - 20, START_X + 20, START_Y, stringBuilder.toString(), true);
            }
        }
    }

    private void addParagraph(float width, float sx,
                              float sy, String text, boolean justify) throws IOException {
        List<String> lines = parseLines(text, width);
        contentStream.beginText();

        contentStream.setFont(FONT, FONT_SIZE);
        contentStream.newLineAtOffset(sx, sy);
        for (String line : lines) {
            START_Y += LEADING;
            if (START_Y < 20) {
                contentStream.endText();
                contentStream = createPDPageContentStream();
                START_Y -= 20;
                contentStream.beginText();
                contentStream.setFont(FONT, FONT_SIZE);
                contentStream.newLineAtOffset(sx, START_Y);
                START_Y += LEADING;
            }
            float charSpacing = 0;
            if (justify) {
                if (line.length() > 1) {
                    float size = FONT_SIZE * FONT.getStringWidth(line) / 1000;
                    float free = width - size;
                    if (free > 0 && !lines.get(lines.size() - 1).equals(line)) {
                        charSpacing = free / (line.length() - 1);
                    }
                }
            }
            contentStream.setCharacterSpacing(charSpacing);
            contentStream.showText(line);
            contentStream.newLineAtOffset(0, LEADING);
        }
        contentStream.endText();
    }

    private List<String> parseLines(String text, float width) throws IOException {
        List<String> lines = new ArrayList<String>();
        int lastSpace = -1;
        while (text.length() > 0) {
            int spaceIndex = text.indexOf(' ', lastSpace + 1);
            if (spaceIndex < 0)
                spaceIndex = text.length();
            String subString = text.substring(0, spaceIndex);
            float size = FONT_SIZE * FONT.getStringWidth(subString) / 1000;
            if (size > width) {
                if (lastSpace < 0) {
                    lastSpace = spaceIndex;
                }
                subString = text.substring(0, lastSpace);
                lines.add(subString);
                text = text.substring(lastSpace).trim();
                lastSpace = -1;
            } else if (spaceIndex == text.length()) {
                lines.add(text);
                text = "";
            } else {
                lastSpace = spaceIndex;
            }
        }
        return lines;
    }
}
