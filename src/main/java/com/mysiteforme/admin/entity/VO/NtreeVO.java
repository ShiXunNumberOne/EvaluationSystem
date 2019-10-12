package com.mysiteforme.admin.entity.VO;

import java.io.Serializable;
import java.util.List;

public class NtreeVO implements Serializable {
    private Long id;

    private Long pid;

    private String name;

    private List<NtreeVO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NtreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<NtreeVO> children) {
        this.children = children;
    }
}
