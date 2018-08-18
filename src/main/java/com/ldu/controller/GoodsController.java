package com.ldu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ldu.pojo.*;
import com.ldu.service.*;
import com.ldu.util.DateUtil;
import com.ldu.util.message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CatelogService catelogService;

    @Autowired
    private UserService userService;

    @Autowired
    private RecordService recordService;

    //公用方法
    static void selectGoodsSort(List<Goods> goodsList, List<GoodsExtend> goodsAndImage, ImageService imageService) {
        for (int j = 0; j < goodsList.size() ; j++) {
            //将用户信息和image信息封装到GoodsExtend类中，传给前台
            GoodsExtend goodsExtend = new GoodsExtend();
            Goods goods = goodsList.get(j);
            List<Image> images = imageService.getImagesByGoodsPrimaryKey(goods.getId());
            goodsExtend.setGoods(goods);
            goodsExtend.setImages(images);
            goodsAndImage.add(j, goodsExtend);
        }
    }

    /**
     * 首页显示商品，每一类商品查询6件，根据最新上架排序 key的命名为catelogGoods1、catelogGoods2....
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/homeGoods")
    public ModelAndView homeGoods() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //商品种类数量
        int catelogSize = 7;
        //每个种类显示商品数量
        int goodsSize = 6;
        String key;
        List<Goods> goodsList = null;
        List<GoodsExtend> goodsAndImage = null;
        goodsList = goodsService.selectOrderByDate();
        goodsAndImage = new ArrayList<GoodsExtend>();
        selectGoodsSort(goodsList, goodsAndImage, imageService);
        key = "catelog" + "Goods" + 0;
        modelAndView.addObject(key, goodsAndImage);
        for (int i = 1; i <= catelogSize; i++) {
            goodsList = goodsService.getGoodsByCatelogOrderByDate(i, goodsSize);
            goodsAndImage = new ArrayList<GoodsExtend>();
            selectGoodsSort(goodsList, goodsAndImage, imageService);
            key = "catelog" + "Goods" + i;
            modelAndView.addObject(key, goodsAndImage);
        }
        modelAndView.setViewName("goods/homeGoods");
        return modelAndView;
    }


    /**
     * 根据输入模糊查询，提供两种排序方式，成色，卖家信誉
     * @param str
     * @param condition
     * @return
     * @Author guoxilong
     */
    @RequestMapping(value = "/search")
    public ModelAndView searchGoods(@RequestParam(value = "str",required = true) String str,
                                    @RequestParam(value = "condition", required = false) String condition)throws Exception {
        List<Goods> goodsList;
        if("credit".equals(condition))
            goodsList = goodsService.searchGoodsOrderByCredit(str,str);
        else
            goodsList = goodsService.searchGoodsOrderByNew(str,str);
        List<GoodsExtend> goodsExtendList = new ArrayList<GoodsExtend>();
        selectGoodsSort(goodsList, goodsExtendList, imageService);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("goodsExtendList", goodsExtendList);
        modelAndView.addObject("search",str);
        modelAndView.setViewName("/goods/searchGoods");
        return modelAndView;
    }

    /**
     * 查询该类商品
     * @param id
     * 要求该参数不为空
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/catelog/{id}")
    public ModelAndView catelogGoods(HttpServletRequest request,@PathVariable("id") Integer id,
                                     @RequestParam(value = "str",required = false) String str) throws Exception {
        List<Goods> goodsList;
        Catelog catelog;
        if(id>0) {
            goodsList = goodsService.getGoodsByCatelog(id, str, str);
            catelog = catelogService.selectByPrimaryKey(id);
        }else{
            goodsList = goodsService.selectOrderByDate();
            catelog = new Catelog(0,"最新发布",10,null);
        }
        List<GoodsExtend> goodsExtendList = new ArrayList<GoodsExtend>();
        selectGoodsSort(goodsList, goodsExtendList, imageService);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("goodsExtendList", goodsExtendList);
        modelAndView.addObject("catelog", catelog);
        modelAndView.addObject("search",str);
        modelAndView.setViewName("/goods/catelogGoods");
        return modelAndView;
    }


    /**
     * 根据商品id查询该商品详细信息
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goodsId/{id}")
    public ModelAndView getGoodsById(@PathVariable("id") Integer id, HttpServletRequest request,
                                     @RequestParam(value = "str",required = false) String str) throws Exception {
        Goods goods = goodsService.getGoodsByPrimaryKey(id);
        User seller = userService.selectByPrimaryKey(goods.getUserId());
        Catelog catelog = catelogService.selectByPrimaryKey(goods.getCatelogId());
        GoodsExtend goodsExtend = new GoodsExtend();
        List<Image> imageList = imageService.getImagesByGoodsPrimaryKey(id);
        goodsExtend.setGoods(goods);
        goodsExtend.setImages(imageList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("goodsExtend", goodsExtend);
        request.getSession().setAttribute("goodsExtend", goodsExtend);
        modelAndView.addObject("seller", seller);
        modelAndView.addObject("search",str);
        modelAndView.addObject("catelog", catelog);
        modelAndView.setViewName("/goods/detailGoods");
        return modelAndView;
    }

    /**
     * 修改商品信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editGoods/{id}")
    public ModelAndView editGoods(@PathVariable("id") Integer id) throws Exception {
        Goods goods = goodsService.getGoodsByPrimaryKey(id);
        //System.out.println(goods);
        List<Image> imageList = imageService.getImagesByGoodsPrimaryKey(id);
        //System.out.println(imageList);
        GoodsExtend goodsExtend = new GoodsExtend();
        goodsExtend.setGoods(goods);
        goodsExtend.setImages(imageList);
        //System.out.println(goodsExtend);
        ModelAndView modelAndView = new ModelAndView();
        //将商品信息添加到model
        modelAndView.addObject("goodsExtend", goodsExtend);
        modelAndView.setViewName("/goods/editGoods");
        return modelAndView;
    }

    /**
     * 跳到打分页面
     * author:guoxilong
     */
    @RequestMapping(value = "/evaluateGoods/{id}")
    public String evaluateGoods(@PathVariable("id") Integer id,
                                 HttpServletRequest request){
        request.getSession().setAttribute("recordId", id);
        return "/goods/goodsScore";
    }

    /**
     * 删除购物记录
     * @param id
     * @return
     * @Author guoxilong
     */
    @RequestMapping(value = "/deleteRecords/{id}")
    public String deleteRecords(@PathVariable("id") Integer id,HttpServletRequest request){
        recordService.deleteRecord(id);
        return "redirect:/user/allGoods";
    }

    /**
     * 提交评价
     * @param score1
     * @param score2
     * @param score3
     * @return
     * @Author guoxilong
     */
    @RequestMapping(value = "/markSubmit", method = RequestMethod.POST)
    public String markSubmit(@RequestParam(value = "score1", required = true) String score1,
                             @RequestParam(value = "score2", required = true) String score2,
                             @RequestParam(value = "score3", required = true) String score3 ,
                             HttpSession session){
        String recordId = String.valueOf(session.getAttribute("recordId"));
        int id = Integer.parseInt(recordId);
        int userId = recordService.selectById(id).getGoods().getUserId();
        User user = userService.selectByPrimaryKey(userId);
        int markNum = user.getMarkNum();
        Double credit;
        if(markNum==0)
            credit = 0.0;
        else
            credit = user.getCredit();
        double s1 = (double)Integer.parseInt(score1.trim());
        double s2 = (double)Integer.parseInt(score2.trim());
        double s3 = (double)Integer.parseInt(score3.trim());
        //计算用户信誉分
        Double newCredit = (Double)(markNum * credit + (s1 + s2 + s3)/3)/(markNum + 1);
        //四舍五入
        double creditValue =new BigDecimal(newCredit).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        user.setCredit(creditValue);
        user.setMarkNum(markNum+1);
        recordService.updateRecord(id);
        userService.updateUserName(user);
        session.setAttribute("cur_user", user);
        return "redirect:/user/allGoods";
    }

    /**
     * 提交商品更改信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editGoodsSubmit")
    public String editGoodsSubmit(HttpServletRequest request,Goods goods) throws Exception {
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        goods.setUserId(cur_user.getId());
        String polish_time = DateUtil.getNowDay();
        goods.setPolishTime(polish_time);
        goodsService.updateGoodsByPrimaryKeyWithBLOBs(goods.getId(), goods);
        return "redirect:/user/allGoods";
    }

    /**
     * 商品下架
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/offGoods")
    public ModelAndView offGoods() throws Exception {

        return null;
    }

    /**
     * 购买商品
     * @param
     * @return
     * @Author guoxilong
     */
    @RequestMapping(value = "/buyGoods")
    public void buyGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url=request.getHeader("Referer");
        message m = new message(true);
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        //用户是否有商品未评价
        Boolean flag = true;
        List<Record> recordList = recordService.selectByUserId(cur_user.getId());
        for(Record record: recordList){
            if(record.getCommentStatus()==false){
                flag = false;
                break;
            }
        }
        //全部已评价
        if(flag){
            GoodsExtend goodsExtend = (GoodsExtend)request.getSession().getAttribute("goodsExtend");
            Goods goods = goodsExtend.getGoods();
            Integer goodsId = goods.getId();
            User seller = userService.selectByPrimaryKey(goods.getUserId());
            Integer price = goodsExtend.getGoods().getPrice();
            Integer savingsOfBuyer = cur_user.getSavings();
            Integer savingsOfSeller = seller.getSavings();
            goods.setCheckStatus(2);
            cur_user.setSavings(savingsOfBuyer - price);
            seller.setSavings(savingsOfSeller + price);
            goodsService.updateGoodsByPrimaryKeyWithBLOBs(goodsId, goods);
            recordService.addRecord(cur_user.getId(), goodsId);
            userService.updateUserName(cur_user);
            userService.updateUserName(seller);
//            return "redirect:/goods/homeGoods";
            response.sendRedirect("/goods/homeGoods");
        }
        else{
            response.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("<script>");
            out.println("alert('您还有购买过的商品未评价,请先去评价再来下单哦!');");
            out.println("window.history.back();</script>");
//            return "redirect:"+url;
        }
    }

    /**
     * 用户删除商品
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteGoods/{id}")
    public String deleteGoods(HttpServletRequest request,@PathVariable("id") Integer id) throws Exception {
        Goods goods = goodsService.getGoodsByPrimaryKey(id);
        //删除商品后，catlog的number-1，user表的goods_num-1，image删除,更新session的值
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        goods.setUserId(cur_user.getId());
        int number = cur_user.getGoodsNum();
        Integer calelog_id = goods.getCatelogId();
        Catelog catelog = catelogService.selectByPrimaryKey(calelog_id);
        catelogService.updateCatelogNum(calelog_id,catelog.getNumber()-1);
        userService.updateGoodsNum(cur_user.getId(),number-1);
        cur_user.setGoodsNum(number-1);
        request.getSession().setAttribute("cur_user",cur_user);//修改session值
        imageService.deleteImagesByGoodsPrimaryKey(id);
        goodsService.deleteGoodsByPrimaryKey(id);
        return "redirect:/user/allGoods";
    }
    /**
     * 发布商品
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/publishGoods")
    public String publishGoods(HttpServletRequest request) {
        //可以校验用户是否登录
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        if(cur_user == null) {
            return "/goods/homeGoods";
        } else {
            return "/goods/pubGoods";
        }
    }
    /**
     * 提交发布的商品信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/publishGoodsSubmit")
    public String publishGoodsSubmit(HttpServletRequest request,Image ima,@ModelAttribute("goods")Goods goods)
            throws Exception {
        //查询出当前用户cur_user对象，便于使用id
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        goods.setUserId(cur_user.getId());
        goods.setCommetNum(0);
        goods.setCheckStatus(0);
        int i = goodsService.addGood(goods,10);//在goods表中插入物品
        //返回插入的该物品的id
        int goodsId = goods.getId();
        String imgUrl = String.valueOf(request.getSession().getAttribute("imgUrl"));
        ima.setGoodsId(goodsId);
        ima.setImgUrl(imgUrl);
        imageService.insert(ima);//在image表中插入商品图片
        //发布商品后，catlog的number+1，user表的goods_num+1，更新session的值
        int number = cur_user.getGoodsNum();
        Integer calelog_id = goods.getCatelogId();
        Catelog catelog = catelogService.selectByPrimaryKey(calelog_id);
        catelogService.updateCatelogNum(calelog_id,catelog.getNumber()+1);
        userService.updateGoodsNum(cur_user.getId(),number+1);
        cur_user.setGoodsNum(number+1);
        request.getSession().setAttribute("cur_user",cur_user);//修改session值
        return "redirect:/user/allGoods";
    }

    /**
     * 上传物品
     * @param session
     * @param myfile
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/uploadFile")
    public  Map<String,Object> uploadFile(HttpSession session,MultipartFile myfile) throws IllegalStateException, IOException{
        //原始名称
        String oldFileName = myfile.getOriginalFilename(); //获取上传文件的原名
        //存储图片的物理路径
        String file_path = session.getServletContext().getRealPath("upload");
        //上传图片
        if(myfile!=null && oldFileName!=null && oldFileName.length()>0){
            //新的图片名称
            String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
            session.setAttribute("imgUrl",newFileName);
            //新图片
            File newFile = new File(file_path+"/"+newFileName);
            //将内存中的数据写入磁盘
            myfile.transferTo(newFile);
            //将新图片名称返回到前端
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("success", "成功啦");
            map.put("imgUrl",newFileName);
            return  map;
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("error","图片不合法");
            return map;
        }
    }
}