import { request } from "../../requests/index.js";
const app = getApp()
Page({
    /**
     * 页面的初始数据
     */
    data: {
        globalid: [],
        sex: 0,
        visitCausearray: ['不限', '大专', '本科', '硕士', '博士'],
        index1: '0',
        visitCause: 0,
        organarray: ['实习', '校招', '社招'],
        index2: '0',
        visitOrganId: 0,
        name: '',
        phone: '',
        idNumber: '',
        subscribeTime: '',
        visitCompany: '',
        interviewee: '',
        visitOrgan: '',
        show: false,
        currentChoose: '',
        apiIdempotentToken:''
    },
    // 封装职位2信息
    job: {
        jobName: '',
        jobIndustry: '',
        jobPlace: '',
        jobEdu: '不限',
        jobDetails: '',
        jobSalary: '',
        email: '',
        jobType: '1',
        jobComId: '',
        jobAuthorId:''
    },

    async onShow(e){
        // 获取当前页面token,实现接口幂等性
        const PLUSH_TOKEN='publish_token'
        const token = wx.getStorageSync(PLUSH_TOKEN)

        if(!token){
            const result = await request({ url: "/order/producer/snow"});
            console.log(result)
            wx.setStorageSync(PLUSH_TOKEN, result);
            this.setData({apiIdempotentToken:result})
        }else{
            this.setData({apiIdempotentToken:token})
        }
    },

    onConfirm(e) {
        this.setData({
            show: false,
            currentChoose: this.formatDate(new Date(e.detail))
        })
    },
    onClose() {
        this.setData({
            show: false
        })
    },
    onCancel() {
        this.setData({
            show: false
        })
    },
    // 学历要求
    bindPickerJobEdu: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value)
        const index = e.detail.value
        this.setData({
            index1: index
        })
        if (index == 0) {
            this.job.jobEdu = '不限'
        } else if (index == 1) {
            this.job.jobEdu = '大专'
        } else if (index == 2) {
            this.job.jobEdu = '本科'
        } else if (index == 3) {
            this.job.jobEdu = '硕士'
        } else if (index == 4) {
            this.job.jobEdu = '博士'
        }
    },
    // 职位分类
    bindPickerJobType: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value)
        const index = e.detail.value
        this.setData({
            index2: index
        })
        const i=parseInt("10", index)+1
        console.log(i)
        this.job.jobType=i;
    },
    setInput: function (e) {
        console.log(e)
        const {
            name
        } = e.target.dataset
        this.job[name] = e.detail.value
        console.log(this.job)
    },
    async setJobComId() {
        const userInfo = wx.getStorageSync("userInfo")
        if (userInfo) {
            this.job.jobComId = userInfo.comId
            this.job.jobAuthorId=userInfo.id
        } else {
            console.log("jobPublish.setJobComId()-->useinfo为null")
        }
    },
    async confirmPublish() {
        this.setJobComId()
        const result = await request({ url: "/own/home/saveJob", data: this.job, method: 'POST', header: { "Content-Type": "application/x-www-form-urlencoded",
        "apiIdempotentToken":this.data.apiIdempotentToken } });
        console.log(result)
        wx.navigateBack({
            delta: 0,
          })
    }
})