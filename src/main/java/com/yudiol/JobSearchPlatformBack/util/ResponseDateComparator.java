package com.yudiol.JobSearchPlatformBack.util;

import com.yudiol.JobSearchPlatformBack.model.Response;

import java.util.Comparator;

public class ResponseDateComparator implements Comparator<Response> {
    @Override
    public int compare(Response o1, Response o2) {
        return (int) (o2.getId() - o1.getId());
    }
}
