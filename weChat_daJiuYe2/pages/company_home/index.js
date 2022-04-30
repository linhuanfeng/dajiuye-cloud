/*
1 当页面被打开的时候 onShow
    0 onshow 不同于onload 无法在形参上接受 options参数
    0.5 判断缓存中有没有token
        1 没有 直接跳转到授权页面
        2 有 直接往下进行
    1 获取url上的参数type
    2 根据type来决定页面标题的数组元素 哪个被激活选中
    2 根据type值发送请求获取订单数据
    3 渲染页面
2 点击不同数据 重新发送请求获取和渲染数据

*/
import { request } from "../../requests/index.js";
Page({

    /**
     * 页面的初始数据
     */
    data: {
        submit_stat: "收藏",
        isCollect_com: false,
        comObj: [],
        tabs: [{
                id: 0,
                value: "公司介绍",
                isActive: true
            },
            {
                id: 1,
                value: "职位",
                isActive: false
            }
        ],
    },
    // 公司信息全局对象
    comInfoStorage: {},
    // 点击收藏图标
    handleCollectAdd() {
        let isCollect_com = false;
        // 1 先获取缓存中的购物车数组
        let collects_com = wx.getStorageSync("collects_com") || [];
        // 2 判断商品对象是否存在于购物车的数组中
        let index = collects_com.findIndex(v => v.comId === this.comInfoStorage.comId);
        if (index != -1) {
            // 能找到 已经收藏过 再点击则取消收藏
            collects_com.splice(index, 1);
            isCollect_com = false;
            wx.showToast({
                title: '取消成功',
                icon: 'success',
                mask: true
            });
            this.setData({
                submit_stat: "收藏"
            })
        } else {
            // 没有收藏过
            collects_com.push(this.comInfoStorage);
            isCollect_com = true;
            wx.showToast({
                title: '收藏成功',
                icon: 'success',
                mask: true
            });
            this.setData({
                submit_stat: "已收藏"
            })
        }
        // 5 把收藏夹重新添加回缓存中
        wx.setStorageSync("collects_com", collects_com);
        this.setData({
            isCollect_com
        })
    },
    // 获取订单列表的方法
    async getOrders(type) {
        const res = await request({ url: "/own/my/orders/all", data: { type } })
        this.setData({
            // orders: res.orders
        })
    },
    // 根据标题的索引来激活选中 标题数组
    changeTitleByIndex(index) {
        // 2 修改源数组
        let { tabs } = this.data;
        tabs.forEach((v, i) => i === index ? v.isActive = true : v.isActive = false);
        // //  3 赋值到data中
        this.setData({
            tabs
        })
    },
    handleTabsItemChange(e) {
        // 1 获取被点击的标题索引
        const { index } = e.detail;
        this.changeTitleByIndex(index);
        // 2 重新发送请求
        this.getOrders(index + 1);
    },
    // 获取公司主页数据
    async getComDetail(comId) {
        const result = await request({ url: "/own/home/comdata", data: { comId } });
        if (result.length != 1) {
            console.log("公司主页获取异常，公司数量不等于1")
        }
        this.setData({
            comObj: result[0]
                // desc: result.data.message[0].jobDesc1,
                // // g表示全局查找
                // mystr2: result.data.message[0].jobDesc1.replace(/\\n/g, '\n')
        })
        this.comInfoStorage = result[0]
            // 1 先获取缓存中的购物车数组
        let collects_com = wx.getStorageSync("collects_com") || [];
        // 2 判断商品对象是否存在于购物车的数组中
        let index = collects_com.findIndex(v => v.comId === this.comInfoStorage.comId);
        if (index != -1) {
            // 说明被收藏了
            console.log("已被收藏")
            this.setData({
                isCollect_com: true,
                submit_stat: "已被收藏"
            })
        }
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        // console.log("onload:"+options);
        this.getComDetail(options.comId);

    }
})