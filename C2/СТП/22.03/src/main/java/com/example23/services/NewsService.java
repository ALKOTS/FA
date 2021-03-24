package com.example23.services;

import com.example23.services;

import java.util.List;

public interface NewsService {

    News create(News news);

    List<News> readAll();

    News read(int id);

    boolean update(News news, int id);

    boolean delete(int id);
}