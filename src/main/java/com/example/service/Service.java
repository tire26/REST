package com.example.service;

import com.example.model.Employer;

import java.util.List;

public interface Service {
    /**
     * Создает нового клиента
     * @param employer - клиент для создания
     */
    void create(Employer employer);

    /**
     * Возвращает список всех имеющихся клиентов
     * @return список клиентов
     */
    List<Employer> readAll();

    /**
     * Возвращает клиента по его ID
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    Employer read(int id);

    /**
     * Обновляет клиента с заданным ID,
     * в соответствии с переданным клиентом
     * @param employer - клиент в соответсвии с которым нужно обновить данные
     * @param id - id клиента которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Employer employer, int id);

    /**
     * Удаляет клиента с заданным ID
     * @param id - id клиента, которого нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    boolean delete(int id);
}
