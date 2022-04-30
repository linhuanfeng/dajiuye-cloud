/*
1 发送请求获取数据
2 点击轮播图 预览大图
    1 给轮播图绑定点击事件
    2 调用小程序的api previewImage
3 点击 加入购物车
    1 先绑定点击事件
    2 获取缓存中的购物车数据 数组格式
    3 先判断 当前的商品是否存在于 购物车
    4 已经存在 修改商品数据 执行购物车数量++ 重新把购物车数组 填充回缓存中
    5 不存在于购物车的数组中 直接给购物车添加一个新元素 新元素带上购买属性 num 重新把购物车数组 填充回缓存中
    6 弹出提示
4 商品收藏
    1 页面onShow的时候 加载缓存中的商品收藏数据
    2 判断当前商品是不是被收藏
        1 是 改变图标
        2 不是 。。
    3 点击商品收藏按钮
        1 判断该商品是否存在于缓存中
        2 已经存在 把该商品删除
        3 没有存在 把该商品添加到收藏数组中 存入缓存中即可
*/
import { request } from "../../requests/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        schoolId: {},
        schoolObj: {},
        // 商品是否被收藏过
        isCollect: false
    },
    // 商品全局对象
    GoodsInfo: {},
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        // 获取当前校招详情信息
        this.getSchoolDetails(options.schoolId);
    },
    // 获取校招详情数据
    async getSchoolDetails(schoolId) {
        const result = await request({ url: "/school/schooldata", data: { schoolId } });
        this.setData({
            schoolObj: result[0]
        })
    },
    // 点击秒杀
    handleSecKill() {
        
        // 1 先获取缓存中的购物车数组
        let cart = wx.getStorageSync("cart") || [];
        // 2 判断商品对象是否存在于购物车的数组中
        let index = cart.findIndex(v => v.goods_id === this.GoodsInfo.goods_id);
        if (index === -1) {
            // 3 不存在 第一次添加
            this.GoodsInfo.num = 1;
            this.GoodsInfo.checked = true;
            cart.push(this.GoodsInfo);
        } else {
            // 4 已经存在购物车数据 执行num++
            cart[index].num++;
        }
        // 5 把购物车重新添加回缓存中
        wx.setStorageSync("cart", cart);
        // 弹窗提示
        wx.showToast({
            title: '加入成功',
            icon: 'success',
            image: '',
            duration: 1500,
            // true 防止用户手抖疯狂点击按钮
            mask: true,
            success: (result) => {

            },
            fail: () => {},
            complete: () => {}
        });
    }
})