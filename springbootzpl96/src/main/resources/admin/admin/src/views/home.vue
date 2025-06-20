<template>
<div class="content">
	<div class="text" :style='{"margin":"50px auto","fontSize":"24px","color":"rgb(51, 51, 51)","textAlign":"center","fontWeight":"bold"}'>欢迎使用 {{this.$project.projectName}}</div>
    <div class="cardView">
        <div class="cards" :style='{"margin":"0 0 20px 0","alignItems":"center","flexDirection":"column","justifyContent":"center","display":"flex"}'>
			<div :style='{"boxShadow":"0 1px 6px rgba(0,0,0,.3)","margin":"5px 10px","borderRadius":"4px","display":"flex"}' v-if="isAuth('xueshengchengji','首页总数')">
				<div :style='{"width":"80px","background":"#b8de4a","height":"80px"}'></div>
				<div :style='{"width":"160px","alignItems":"center","flexDirection":"column","justifyContent":"center","display":"flex"}'>
					<div :style='{"margin":"5px 0","lineHeight":"24px","fontSize":"20px","color":"#333","fontWeight":"bold","height":"24px"}'>{{xueshengchengjiCount}}</div>
					<div :style='{"margin":"5px 0","lineHeight":"24px","fontSize":"16px","color":"#666","height":"24px"}'>学生成绩总数</div>
				</div>
			</div>
			<div :style='{"boxShadow":"0 1px 6px rgba(0,0,0,.3)","margin":"5px 10px","borderRadius":"4px","display":"flex"}' v-if="isAuth('biyezige','首页总数')">
				<div :style='{"width":"80px","background":"#b8de4a","height":"80px"}'></div>
				<div :style='{"width":"160px","alignItems":"center","flexDirection":"column","justifyContent":"center","display":"flex"}'>
					<div :style='{"margin":"5px 0","lineHeight":"24px","fontSize":"20px","color":"#333","fontWeight":"bold","height":"24px"}'>{{biyezigeCount}}</div>
					<div :style='{"margin":"5px 0","lineHeight":"24px","fontSize":"16px","color":"#666","height":"24px"}'>毕业资格总数</div>
				</div>
			</div>
        </div>
        <div style="display: flex;align-items: center;width: 100%;margin-bottom: 10px;">
            <el-card style="width: 25%;margin: 0 10px;" v-if="isAuth('xueshengchengji','首页统计')">
                <div id="xueshengchengjiChart1" style="width:100%;height:400px;"></div>
            </el-card>
            <el-card style="width: 25%;margin: 0 10px;" v-if="isAuth('xueshengchengji','首页统计')">
                <div id="xueshengchengjiChart2" style="width:100%;height:400px;"></div>
            </el-card>
            <el-card style="width: 25%;margin: 0 10px;" v-if="isAuth('xueshengchengji','首页统计')">
                <div id="xueshengchengjiChart3" style="width:100%;height:400px;"></div>
            </el-card>
            <el-card style="width: 25%;margin: 0 10px;" v-if="isAuth('biyezige','首页统计')">
                <div id="biyezigeChart1" style="width:100%;height:400px;"></div>
            </el-card>
        </div>
    </div>
</div>
</template>
<script>
//4
import router from '@/router/router-static'
import * as echarts from 'echarts'
export default {
	data() {
		return {
            xueshengchengjiCount: 0,
            biyezigeCount: 0,
		};
	},
  mounted(){
    this.init();
    this.getxueshengchengjiCount();
    this.xueshengchengjiChat1();
    this.xueshengchengjiChat2();
    this.xueshengchengjiChat3();
    this.getbiyezigeCount();
    this.biyezigeChat1();
  },
  methods:{
    init(){
        if(this.$storage.get('Token')){
        this.$http({
            url: `${this.$storage.get('sessionTable')}/session`,
            method: "get"
        }).then(({ data }) => {
            if (data && data.code != 0) {
            router.push({ name: 'login' })
            }
        });
        }else{
            router.push({ name: 'login' })
        }
    },
    getxueshengchengjiCount() {
        this.$http({
            url: `xueshengchengji/count`,
            method: "get"
        }).then(({
            data
        }) => {
            if (data && data.code == 0) {
                this.xueshengchengjiCount = data.data
            }
        })
    },

    xueshengchengjiChat1() {
      this.$nextTick(()=>{
        // kemu kemu
        //  chengji

        var xueshengchengjiChart1 = echarts.init(document.getElementById("xueshengchengjiChart1"),'macarons');
        this.$http({
            url: `xueshengchengji/value/kemu/chengji`,
            method: "get",
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].kemu);
                    yAxis.push(parseFloat((res[i].total)));
                    pArray.push({
                        value: parseFloat((res[i].total)),
                        name: res[i].kemu
                    })
                }
                var option = {};
                option = {
                    title: {
                        text: '科目成绩',
                        left: 'center'
                    },
                    tooltip: {
                      trigger: 'item',
                      formatter: '{b} : {c}'
                    },
                    xAxis: {
                        type: 'category',
                        data: xAxis
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: yAxis,
                        type: 'bar'
                    }]
                };
                // 使用刚指定的配置项和数据显示图表。
                xueshengchengjiChart1.setOption(option);
                  //根据窗口的大小变动图表
                window.onresize = function() {
                    xueshengchengjiChart1.resize();
                };
            }
        });
      })
    },

    xueshengchengjiChat2() {
      this.$nextTick(()=>{

        var xueshengchengjiChart2 = echarts.init(document.getElementById("xueshengchengjiChart2"),'macarons');
        this.$http({
            url: "xueshengchengji/group/shifouhege",
            method: "get",
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].shifouhege);
                    yAxis.push(parseFloat((res[i].total)));
                    pArray.push({
                        value: parseFloat((res[i].total)),
                        name: res[i].shifouhege
                    })
                }
                var option = {};
                option = {
                        title: {
                            text: '合格情况',
                            left: 'center'
                        },
                        tooltip: {
                          trigger: 'item',
                          formatter: '{b} : {c} ({d}%)'
                        },
                        series: [
                            {
                                type: 'pie',
                                radius: '55%',
                                center: ['50%', '60%'],
                                data: pArray,
                                emphasis: {
                                    itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                };
                // 使用刚指定的配置项和数据显示图表。
                xueshengchengjiChart2.setOption(option);
                  //根据窗口的大小变动图表
                window.onresize = function() {
                    xueshengchengjiChart2.resize();
                };
            }
        });
      })
    },

    xueshengchengjiChat3() {
      this.$nextTick(()=>{
        // xingming xingming
        //  xuefen

        var xueshengchengjiChart3 = echarts.init(document.getElementById("xueshengchengjiChart3"),'macarons');
        this.$http({
            url: `xueshengchengji/value/xingming/xuefen`,
            method: "get",
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].xingming);
                    yAxis.push(parseFloat((res[i].total)));
                    pArray.push({
                        value: parseFloat((res[i].total)),
                        name: res[i].xingming
                    })
                }
                var option = {};
                option = {
                    title: {
                        text: '学生学分',
                        left: 'center'
                    },
                    tooltip: {
                      trigger: 'item',
                      formatter: '{b} : {c}'
                    },
                    xAxis: {
                        type: 'category',
                        data: xAxis
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: yAxis,
                        type: 'bar'
                    }]
                };
                // 使用刚指定的配置项和数据显示图表。
                xueshengchengjiChart3.setOption(option);
                  //根据窗口的大小变动图表
                window.onresize = function() {
                    xueshengchengjiChart3.resize();
                };
            }
        });
      })
    },




    getbiyezigeCount() {
        this.$http({
            url: `biyezige/count`,
            method: "get"
        }).then(({
            data
        }) => {
            if (data && data.code == 0) {
                this.biyezigeCount = data.data
            }
        })
    },

    biyezigeChat1() {
      this.$nextTick(()=>{

        var biyezigeChart1 = echarts.init(document.getElementById("biyezigeChart1"),'macarons');
        this.$http({
            url: "biyezige/group/biyeqingkuang",
            method: "get",
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].biyeqingkuang);
                    yAxis.push(parseFloat((res[i].total)));
                    pArray.push({
                        value: parseFloat((res[i].total)),
                        name: res[i].biyeqingkuang
                    })
                }
                var option = {};
                option = {
                        title: {
                            text: '毕业情况',
                            left: 'center'
                        },
                        tooltip: {
                          trigger: 'item',
                          formatter: '{b} : {c} ({d}%)'
                        },
                        series: [
                            {
                                type: 'pie',
                                radius: ['25%', '55%'],
                                center: ['50%', '60%'],
                                data: pArray,
                                emphasis: {
                                    itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                };
                // 使用刚指定的配置项和数据显示图表。
                biyezigeChart1.setOption(option);
                  //根据窗口的大小变动图表
                window.onresize = function() {
                    biyezigeChart1.resize();
                };
            }
        });
      })
    },






  }
};
</script>
<style lang="scss" scoped>
    .cardView {
        display: flex;
        flex-wrap: wrap;
        width: 100%;

        .cards {
            display: flex;
            align-items: center;
            width: 100%;
            margin-bottom: 10px;
            justify-content: center;
            .card {
                width: calc(25% - 20px);
                margin: 0 10px;
                /deep/.el-card__body{
                    padding: 0;
                }
            }
        }
    }
</style>
