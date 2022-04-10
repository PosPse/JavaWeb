package Test;

import com.popse.pojo.Academy;
import com.popse.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @Description : TODO
 * @Author : PoPse
 * @Date : 2022/4/10 14:46
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
        Academy academy = session.selectOne("findAcademyById", 1);
        System.out.println(academy);
        session.close();
    }

    /**
     * @Description: 根据学院名模糊查询学院信息列表 或 查询所有学院信息列表
     * @Param : null
     * @Return: void
     **/
    @Test
    public void findAcademyByNameTest() {
        SqlSession session = MyBatisUtils.getSession();
        List<Academy> academyList = session.selectList("findAcademyByName", "");
        for(Academy academy : academyList) {
            System.out.println(academy);
        }
    }

    /**
     * @Description: 添加学院信息
     * @Param : null
     * @Return: void
     **/
    @Test
    public void addAcademyTest(){
        SqlSession session = MyBatisUtils.getSession();
        Academy academy = new Academy("文学院");
        int count = session.insert("addAcademy", academy);
        if(count > 0){
            System.out.println("您成功插入了"+count+"条数据！");
        }else{
            System.out.println("执行插入操作失败！！！");
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
        Academy academy = new Academy(1, "计算机学院");
        int count = session.update("updateAcademy", academy);
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
        int count = session.delete("deleteAcademy", 8);
        if(count > 0){
            System.out.println("您成功删除了"+count+"条数据！");
        }else{
            System.out.println("执行删除操作失败！！！");
        }
        session.commit();
        session.close();
    }
}
