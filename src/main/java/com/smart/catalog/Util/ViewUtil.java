package com.smart.catalog.Util;

public class ViewUtil {
    public static String bookViePagination(int size, int current, int pagesize,String search)
    {
        boolean first = false,second = false;
        StringBuilder builder = new StringBuilder();
        if (current > size && size != 0)
            throw new IllegalStateException("Illegal page on pagination");
        builder.append("<li class=\"page-item\"><a class=\"page-link\" href=\"/books?size=")
                .append(pagesize)
                .append("&page=1")
                .append(search)
                .append("\">1</a></li>");
        String base = "<li class=\"page-item\"><a class=\"page-link\" href=\"/books?size="+pagesize+"&page=";
        String mid = search+"\">";
        String end ="</a></li>";
        String star = "<li class=\"page-item\"><a class=\"page-link\">...</a></li>";
        for (int i = 1; i < size; i++)
        {
            if (i == current - 1 || i == size - 1)
            {
                builder.append(base);
                builder.append(i+1);
                builder.append(mid);
                builder.append(i+1);
                builder.append(end);
            }
            else if ((i+1 < current && current-i-1 <=2) || (current < i+1 && i+1-current <=2))
            {
                builder.append(base);
                builder.append(i+1);
                builder.append(mid);
                if (i+1==current)
                    builder.append("<b>");
                builder.append(i+1);
                if (i+1==current)
                    builder.append("</b>");
                builder.append(end);
            }
            else
            {
                if (i+1 < current && !first)
                {
                    builder.append(star);
                    first = true;
                }
                else if (current < i+1 && !second)
                {
                    builder.append(star);
                    second = true;
                }
            }
        }
        return builder.toString();
    }
}
