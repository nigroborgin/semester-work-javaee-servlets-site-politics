package ru.kpfu.itis.shkalin.service.crud;

import ru.kpfu.itis.shkalin.dao.Dao;
import ru.kpfu.itis.shkalin.dao.AccountDao;
import ru.kpfu.itis.shkalin.dto.AccountFullDto;
import ru.kpfu.itis.shkalin.entity.Account;
import ru.kpfu.itis.shkalin.service.converter.ConverterService;
import ru.kpfu.itis.shkalin.service.converter.ConverterServiceImpl;
import ru.kpfu.itis.shkalin.util.PasswordUtil;

import java.util.List;
import java.util.stream.Collectors;

public class AccountCrudService implements CrudService<AccountFullDto> {

    private final Dao<Account> dao;
    private final ConverterService converter;

    public AccountCrudService(AccountDao dao, ConverterServiceImpl converter) {
        this.dao = dao;
        this.converter = converter;
    }

    @Override
    public void create(AccountFullDto dto) {
        Account account = (Account) converter.getUpdateEntity(new Account(), dto);
        dao.create(account);
    }

    @Override
    public AccountFullDto get(int id) {
        Account account = dao.get(id);
        if (account != null) {
            return (AccountFullDto) converter.getUpdateDto(account, new AccountFullDto());
        } else{
            return null;
        }
    }

    @Override
    public AccountFullDto get(String name) {
        Account account = dao.get(name);
        if (account != null) {
            return (AccountFullDto) converter.getUpdateDto(account, new AccountFullDto());
        } else{
            return null;
        }
    }

    @Override
    public List<AccountFullDto> getAll() {
        return dao.getAll().stream()
                .map(account -> (AccountFullDto) converter.getUpdateDto(account, new AccountFullDto()))
                .collect(Collectors.toList());
    }

    @Override
    public void update(AccountFullDto dto) {
        Account account = (Account) converter.getUpdateEntity(new Account(), dto);
        String password = account.getPassword();
        if (password != null) {
            account.setPassword(PasswordUtil.encrypt(password));
        }
        dao.update(account);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
