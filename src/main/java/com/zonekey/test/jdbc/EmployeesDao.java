package com.zonekey.test.jdbc;

import java.util.ArrayList;
import java.util.List;

import com.zonekey.test.entity.Employees;

public class EmployeesDao extends BaseDAO<Employees> {

    // 添加员工信息的操作
    public boolean addEmployees(final Employees employees) throws Exception {
        save(employees);
        return true;
    }

    // 将员工信息添加到表格中
    public List<Employees> addEmployees(int id) throws Exception {
        List<Employees> lstEmployees = new ArrayList<Employees>();
        Employees employees = findById(id);
        // 将当前封转好的数据装入对象中
        lstEmployees.add(employees);
        return lstEmployees;
    }

    public void deleteEmp(final Employees entity) throws Exception {
        this.delete(entity);
    }

    public void updateEmp(final Employees entity) throws Exception {
        this.update(entity);
    }


}