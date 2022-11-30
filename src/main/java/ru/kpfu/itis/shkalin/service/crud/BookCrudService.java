package ru.kpfu.itis.shkalin.service.crud;

import ru.kpfu.itis.shkalin.dao.BookDao;
import ru.kpfu.itis.shkalin.dao.Dao;
import ru.kpfu.itis.shkalin.dto.BookFullDto;
import ru.kpfu.itis.shkalin.entity.Book;
import ru.kpfu.itis.shkalin.service.converter.ConverterService;
import ru.kpfu.itis.shkalin.service.converter.ConverterServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

public class BookCrudService implements CrudService<BookFullDto>{

    private final Dao<Book> bookDao;
    private final ConverterService converter;

    public BookCrudService(BookDao bookDao, ConverterServiceImpl converter) {
        this.bookDao = bookDao;
        this.converter = converter;
    }

    @Override
    public void create(BookFullDto dto) {
        Book book = (Book) converter.getUpdateEntity(new Book(), dto);
        bookDao.create(book);
    }

    @Override
    public BookFullDto get(int id) {
        Book book = bookDao.get(id);
        return (BookFullDto) converter.getUpdateDto(book, new BookFullDto());
    }

    @Override
    public BookFullDto get(String name) {
        return null;
    }

    @Override
    public List<BookFullDto> getAll() {
        return bookDao.getAll().stream()
                .map(book -> (BookFullDto) converter.getUpdateDto(book, new BookFullDto()))
                .collect(Collectors.toList());
    }

    @Override
    public void update(BookFullDto bookFullDto) {
        Book book = (Book) converter.getUpdateEntity(new Book(), bookFullDto);
        bookDao.update(book);
    }

    @Override
    public void delete(int id) {
        bookDao.delete(id);
    }
}
