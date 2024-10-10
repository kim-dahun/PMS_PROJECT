//package com.prod.pms;
//
//import com.prod.pms.api.menu.service.MenuService;
//import com.prod.pms.api.menu.vo.MenuListVo;
//import com.prod.pms.domain.menu.entity.MenuList;
//import com.prod.pms.domain.menu.repository.MenuRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.LinkedList;
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//public class treeNodeTest {
//
//    @Autowired
//    MenuService menuService;
//
//    @Autowired
//    MenuRepository menuRepository;
//
//    @Test
//    void test(){
//        List<MenuList> menuListList = new LinkedList<>();
//        for(int i = 1; i<30; i++){
//            MenuList menuList = new MenuList(null, "메뉴"+i,"/test/"+i, (i<=3 ? -1L : (i%2==1 ? 3 : 13)));
//            menuListList.add(menuList);
//        }
//        menuRepository.saveAll(menuListList);
//        MenuListVo menuListVo = getNodeMenu();
//        List<MenuList> menuLists = menuRepository.findAll();
//        List<MenuListVo> nodeMenuListVos = getTreeNodeList(menuLists, menuListVo.getMenuNo());
//        menuListVo.setChildren(getTreeMenuList(menuLists,nodeMenuListVos));
//        System.out.println(menuListVo.getChildren());
//
//
//    }
//
//    public MenuListVo getNodeMenu(){
//        return new MenuListVo(-1L,"node","",null,null);
//    }
//
//
//    public List<MenuListVo> getTreeNodeList(List<MenuList> menuLists, Long parentNo){
//        return menuLists.stream().filter(x->
//                x.getMenuParentNo().equals(parentNo)
//        ).map(MenuListVo::fromEntity).toList();
//    }
//
//    public List<MenuListVo> getTreeMenuList(List<MenuList> menuList, List<MenuListVo> nodeMenuList){
//        List<MenuListVo> menuListVos = new LinkedList<>();
//        for(MenuListVo menuListVo : nodeMenuList){
//            List<MenuListVo> childMenuList = getTreeNodeList(menuList, menuListVo.getMenuNo());
//            if(!childMenuList.isEmpty()){
//                getTreeMenuList(menuList,childMenuList);
//            }
//            menuListVo.setChildren(childMenuList);
//            menuListVos.add(menuListVo);
//        }
//        return menuListVos;
//    }
//
//}
