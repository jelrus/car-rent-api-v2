package com.backend.dao;

import com.backend.models.table.Faq;

import java.util.List;

public interface FaqDao {
    void create(Faq faq);

    List<Faq> findAll();
}
