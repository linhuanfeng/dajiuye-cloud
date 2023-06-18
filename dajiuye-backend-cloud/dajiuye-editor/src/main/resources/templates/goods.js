new Vue({
   el:"#goodsApp",
   data:{
       goods: {id:"",name:"",content:""},
       goodsList:[]
   },
    methods:{
        add: function () {
            this.goods.content = UE.getEditor('container').getEditor('container');
            console.info("name:"+this.goods.name+",content:"+this.goods.content);
            var _this=this;
            axios.post('/goods/save',_this.goods
            )
                .then(function (response) {
                    _this.findAll();
                    console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        loadeditor:function () {
            UE.getEditor('container');
        }
     },
    created(){
        this.loadeditor();
    }
});

