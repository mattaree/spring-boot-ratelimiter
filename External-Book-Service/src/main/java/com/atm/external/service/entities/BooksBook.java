package com.atm.external.service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "books_book")

public class BooksBook {

    @Id
    private Integer id;

    @Column(name = "download_count")
    private Integer downloadCount;

    @Column(name = "gutenberg_id")
    private Integer gutenbergId;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "title")
    private String title;
}
