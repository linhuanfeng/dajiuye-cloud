/*
1 点击“+”触发tap点击事件
    1 调用小程序内置的选择图片的api
    2 获取到图片的路径数组
    3 把图片路径存到data的变量中
    4 页面就可以根据图片数组进行循环显示自定义组件
2 点击自定义图片组件
    1 获取被点击元素的索引
    2 获取 data中的图片数组
    3 根据索引 数组中删除对应的元素
    4 把数组重新设置回data中
3 点击“提交”
    1 获取文本域的内容 类似普通输入框的获取
        1 data中定义变量 表示 输入款内容
        2 文本域 绑定事件 事件触发的时候 把输入框的内容存入到data变量中
    2 对这些内容 合法性验证
    3 验证通过 用户选择的图片 上传到专门的图片服务器 返回图片外网的链接
        1 遍历图片数组
        2 挨个上传
        3 自己再来维护图片数组 存放 图片上传后的外网链接
    4 文本域 和 外网的图片的路径 一起提交到服务器 前端的模拟 并不会发送请求到后台
    5 清空当前页面
    6 返回上一页
*/
Page({

    /**
     * 页面的初始数据
     */
    data: {
        tabs: [{
                id: 0,
                value: "体验问题",
                isActive: true
            },
            {
                id: 1,
                value: "商品、商家投诉",
                isActive: false
            }
        ],
        chooseImgs: [],
        // 文本域内容
        textVal: ""
    },
    // 外网的图片的路径数组
    UploadImgs: [],

    handleTabsItemChange(e) {
        // 1 获取被点击的标题索引
        const { index } = e.detail;
        // // 修改源数组
        let { tabs } = this.data;
        tabs.forEach((v, i) => i === index ? v.isActive = true : v.isActive = false);
        // //  3 赋值到data中
        this.setData({
            tabs
        })
    },
    // 点击“+”选择图片
    handleChooseImg() {
        // 2 调用程序内置的api
        wx.chooseImage({
            // 同时选中的图片的数量
            count: 9,
            // 图片的格式 原图 压缩
            sizeType: ['original', 'compressed'],
            // 图片的来源 相册 照相机
            sourceType: ['album', 'camera'],
            success: (result) => {
                this.setData({
                    // 把图片数组进行拼接
                    chooseImgs: [...this.data.chooseImgs, ...result.tempFilePaths]
                })
            },
            fail: () => {},
            complete: () => {}
        });

    },
    // 点击自定义组件
    handleRemoveImg(e) {
        // 2 获取被点击的组件的索引
        const { index } = e.currentTarget.dataset;
        // 3 获取data中的图片数组
        let { chooseImgs } = this.data;
        // 4 删除元素
        chooseImgs.splice(index, 1);
        this.setData({
            chooseImgs
        })
    },
    // 文本域的输入事件
    handleTextInput(e) {
        this.setData({
            textVal: e.detail.value
        })
    },
    // 提交按钮的点击
    handleFormSubmit() {
        // 获取文本域的内容 图片数组
        const { textVal, chooseImgs } = this.data;
        // 合法性的验证
        if (!textVal) {
            // 不合法
            wx.showToast({
                title: '输入不合法',
                mask: true
            });
            return;
        }
        // 3 准备上传图片  到专门的图片服务器
        // 上传文件的api不支持多个文件同时上传 遍历数组 挨个上传
        // 显示正在下载图标
        wx.showLoading({
            title: "正在上传中",
            mask: true,
        });
        // 
        if (chooseImgs.length != 0) {
            chooseImgs.forEach((v, i) => {
                wx.uploadFile({
                    // 图片要上传到哪里
                    url: 'http://img.coolcr.cn/api/upload',
                    // 被上传文件的路径
                    filePath: v,
                    // 上传文件的名称 后台获取文件 file
                    name: "image",
                    // 顺带的文本信息
                    formData: {},
                    success: (result) => {
                        console.log(result);
                        let url = JSON.parse(result.data).data.url;
                        this.UploadImgs.push(url);
                        console.log("this.UploadImgs:" + this.UploadImgs);
                        // 所有的图片上传完毕才触发
                        if (i === chooseImgs.length - 1) {
                            wx.hideLoading();
                            console.log("把文本的内容和外网的图片数组 提交到后台中");
                            // 提交都成功了
                            // 重置页面
                            this.setData({
                                    textVal: "",
                                    chooseImgs: []
                                })
                                // 返回上一个页面
                            wx.navigateBack({
                                delta: 1
                            });
                        }
                    }
                });
            })
        } else {
            wx.hideLoading();
            console.log("只是提交了文本");
            wx.navigateBack({
                delta: 1
            });
        }
    },

})