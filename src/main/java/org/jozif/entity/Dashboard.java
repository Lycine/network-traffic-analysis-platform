package org.jozif.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by hongyu on 2017/5/25.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Dashboard {
    private int id;
    private String link;
    private String title;
    private String content;
    private int from;
    private Date addTime;
    private int isDel;
    private String note;

    @Override
    public String toString() {
        return "Dashboard{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", from=" + from +
                ", addTime=" + addTime +
                ", isDel=" + isDel +
                ", note='" + note + '\'' +
                '}';
    }
}
