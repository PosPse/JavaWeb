package Test;

import com.popse.dao.AcademyMapper;
import com.popse.pojo.Academy;
import com.popse.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @Description : TODO
 * @Author : PoPse
 * @Date : 2022/4/11 0:03
 * @Version : 1.0
 **/
public class AcademyTest {

    /**
     * @Description: 根据学院ID获取学院信息
     * @Param : null
     * @Return: void
     **/
    @Test
    public void findAcademyByIdTest() {
        SqlSession session = MyBatisUtils.getSession();
        AcademyMapper mapper = session.getMapper(AcademyMapper.class);
        Academy academy = mapper.findAcademyById(1);
        System.out.println(academy);
        session.close();
    }

    /**
     * @Description: 据学院名模糊查询学院信息列表 或 查询所有学院信息列表
     * @Param : null
     * @Return: void
     **/
    @Test
    public void findAcademyByNameTest() {
        SqlSession session = MyBatisUtils.getSession();
        AcademyMapper mapper = session.getMapper(AcademyMapper.class);
        ArrayList<Academy> academyList = mapper.findAcademyByName("");
        for (Academy academy : academyList) {
            System.out.println(academy);
        }
        session.close();
    }

    /**
     * @Description: 添加学院信息
     * @Param : null
     * @Return: void
     **/
    @Test
    public void addAcademyTest() {
        SqlSession session = MyBatisUtils.getSession();
        AcademyMapper mapper = session.getMapper(AcademyMapper.class);
        Academy academy = new Academy("化工学院");
        int count = mapper.addAcademy(academy);
        if(count > 0){
            System.out.println("您成功添加了"+count+"条数据！");
        }else{
            System.out.println("执行修改操作失败！！！");
        }
        session.commit();
        session.close();
    }

    /**
     * @Description: 修改学院信息
     * @Param : null
     * @Return: void
     **/
    @Test
    public void updateAcademyTest() {
        SqlSession session = MyBatisUtils.getSession();
        AcademyMapper mapper = session.getMapper(AcademyMapper.class);
        Academy academy = new Academy(1, "计算机科学与工程学院");
        int count = mapper.updateAcademy(academy);
        if(count > 0){
            System.out.println("您成功修改了"+count+"条数据！");
        }else{
            System.out.println("执行修改操作失败！！！");
        }
        session.commit();
        session.close();
    }

    /**
     * @Description: 删除学院
     * @Param : null
     * @Return: void
     **/
    @Test
    public void deleteAcademyTest() {
        SqlSession session = MyBatisUtils.getSession();
        AcademyMapper mapper = session.getMapper(AcademyMapper.class);
        int count = mapper.deleteAcademy(10);
        if(count > 0){
            System.out.println("您成功删除了"+count+"条数据！");
        }else{
            System.out.println("执行删除操作失败！！！");
        }
        session.commit();
        session.close();
    }
}
