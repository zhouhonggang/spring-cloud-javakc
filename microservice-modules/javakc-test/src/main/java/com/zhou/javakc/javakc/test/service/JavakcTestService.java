package com.zhou.javakc.javakc.test.service;

import com.zhou.javakc.component.data.entity.test.Test;
import com.zhou.javakc.component.data.jpa.base.BaseService;
import com.zhou.javakc.component.data.jpa.dynamic.criterion.Criteria;
import com.zhou.javakc.component.data.jpa.dynamic.restrictions.Restrictions;
import com.zhou.javakc.javakc.test.dao.JavakcTestDao;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-10-18 14:07
 */
@Service
public class JavakcTestService extends BaseService<Test, JavakcTestDao> {

    @CacheEvict(value="test", key="'javakc'", allEntries = true)
    @Transactional(readOnly=false)
    public Test save(Test entity)
    {
        if(StringUtils.isEmpty(entity.getId()))
        {
            entity.setRdate(new Timestamp(System.currentTimeMillis()));
        }
        else
        {
            entity.setUdate(new Timestamp(System.currentTimeMillis()));
        }
        return dao.save(entity);
    }

    @Cacheable(value="test", key = "'javakc'+#entity.offset")
    public Page<Test> findAll(Test entity)
    {
        Criteria<Test> criteria = new Criteria<>();

        if(!StringUtils.isEmpty(entity.getName()))
        {
            criteria.add(Restrictions.like("name", entity.getName()));
        }
//        if(!StringUtils.isEmpty(entity.getSrange()) && !StringUtils.isEmpty(entity.getErange())
//            && entity.getSrange() < entity.getErange())
//        {
//            criteria.add(Restrictions.between("age", entity.getSrange(), entity.getErange()));
//        }
        if(!StringUtils.isEmpty(entity.getSrange()))
        {
            criteria.add(Restrictions.gte("age", entity.getSrange()));
        }
        if(!StringUtils.isEmpty(entity.getErange()))
        {
            criteria.add(Restrictions.lte("age", entity.getErange()));
        }
        if(!StringUtils.isEmpty(entity.getSex()))
        {
            criteria.add(Restrictions.eq("sex", entity.getSex()));
        }
        PageRequest pageable = PageRequest.of(entity.getOffset(), entity.getLimit());
        return dao.findAll(criteria, pageable);
    }

    public void export(HttpServletResponse response) throws IOException {
        //1.查询全部需要导出的数据集
        List<Test> list = dao.findAll();
        //2.生成excel表格文件并插入数据集
        Workbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = (HSSFSheet) workbook.createSheet("测试模块1");

        //创建第一行(标题行)
        HSSFRow row = sheet.createRow(0);
        //名称, 年龄, 性别, 生日, 住址
        String[] titles = new String[]{"名称", "年龄", "性别", "生日", "住址"};
        for(int i=0;i<titles.length;i++)
        {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
        }

        //创建后续行(数据行)
        for(int i=0;i<list.size();i++)
        {
            Test entity = list.get(i);
            HSSFRow dataRow = sheet.createRow(i+1);
            dataRow.createCell(0).setCellValue( entity.getName() );
            dataRow.createCell(1).setCellValue( entity.getAge() );
            dataRow.createCell(2).setCellValue( entity.getSex()==1?"男":"女" );

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String birthday = "";
            if(!StringUtils.isEmpty(entity.getBirthday()))
            {
                birthday = format.format(entity.getBirthday());
            }
            dataRow.createCell(3).setCellValue( birthday );
            dataRow.createCell(4).setCellValue( entity.getAddress() );
        }

        //汇总行
        HSSFRow sumRow = sheet.createRow(list.size()+1);
        sumRow.createCell(4).setCellValue("共导出:"+list.size()+"行!");

        //3.下载excel到客户端
        response.reset();
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+System.currentTimeMillis()+".xls");

        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
        out.close();
    }

    public List<Map<String, Object>> queryGroupBySex()
    {
        return dao.queryGroupBySex();
    }

}
