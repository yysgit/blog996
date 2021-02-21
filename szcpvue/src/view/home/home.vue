<template>
  <div>
    <div style="min-width:1000px">
    <Row :gutter="20">
      <i-col :xs="12" :md="8" :lg="4" v-for="(infor, i) in inforCardData" :key="`infor-${i}`" style="height: 120px;padding-bottom: 10px;">
        <infor-card shadow :color="infor.color" :icon="infor.icon" :icon-size="36">
          <count-to :end="infor.count" count-class="count-style"/>
          <p>{{ infor.title }}</p>
        </infor-card>
      </i-col>
    </Row>
    <Row :gutter="20" style="margin-top: 10px;">
      <i-col :md="24" :lg="8" style="margin-bottom: 20px;">
        <Card shadow>
          <chart-pie style="height: 300px;" :value="pieData" text="订单费用"></chart-pie>
        </Card>
      </i-col>
      <i-col :md="24" :lg="16" style="margin-bottom: 20px;">
        <Card shadow>
          <chart-bar style="height: 300px;" :value="barData" text="近周订单数量"/>
        </Card>
      </i-col>
    </Row>
    <Row>
      <Card shadow>
        <example style="height: 350px;" :value="weekOrderMoney"  text="近周订单金额"/>
      </Card>
    </Row>
    </div>
  </div>
</template>

<script>
import InforCard from '_c/info-card'
import CountTo from '_c/count-to'
import { ChartPie, ChartBar } from '_c/charts'
import Example from './example.vue'
import { mapActions } from 'vuex'
import Vue from 'vue'
export default {
  name: 'home',
  components: {
    InforCard,
    CountTo,
    ChartPie,
    ChartBar,
    Example
  },
  data () {
    return {
      inforCardData: [
        { title: '近周新增用户', icon: 'md-person-add', count: 0, color: '#2d8cf0' },
        { title: '平台总用户量', icon: 'ios-people', count: 0, color: '#19be6b' },
        { title: '近周新增设备', icon: 'md-phone-portrait', count: 0, color: '#ff9900' },
        { title: '平台总设备量', icon: 'md-phone-portrait', count: 0, color: '#ed3f14' },
        { title: '近周新增订单', icon: 'md-chatbubbles', count: 0, color: '#E46CBB' },
        { title: '平台订单总金额', icon: 'logo-usd', count: 0, color: '#9A66E4' }
      ],
      pieData: [
        { value: 0, name: '收费' },
        { value: 0, name: '免费' },
      ],
      barData: {

      },
      weekOrderMoney:{

      }
    }
  },

  created() {
    //注册that
    this.setThat();

    //初始化首页统计
    this.queryHomeData();
  },

  mounted () {
    //
  },

  methods: {
    ...mapActions([
      'getHomeData',
      'setThatVue',

    ]),


    //设置that
    setThat(){
      var that=this;
      this.setThatVue({that}).then(res => {
        console.log("设置that:"+res);
      })

    },

    queryHomeData() {

     /* //发送请求
      this.getHomeData().then(res => {
        this.inforCardData[0].count=res.data.homeData.weekUserNum;
        this.inforCardData[1].count=res.data.homeData.weekUserAllNum;
        this.inforCardData[2].count=res.data.homeData.weekDeviceNum;
        this.inforCardData[3].count=res.data.homeData.weekDeviceAllNum;
        this.inforCardData[4].count=res.data.homeData.weekOrderNum;
        this.inforCardData[5].count=res.data.homeData.weekOrderAllNum;
        this.pieData[0].value=res.data.homeData.weekOrderChargeNum;
        this.pieData[1].value=res.data.homeData.weekOrderFreeNum;


        Vue.set(this.barData,res.data.weekDataList.weekDate6,res.data.homeWeekOrderData.weekOrderNum6);
        Vue.set(this.barData,res.data.weekDataList.weekDate5,res.data.homeWeekOrderData.weekOrderNum5);
        Vue.set(this.barData,res.data.weekDataList.weekDate4,res.data.homeWeekOrderData.weekOrderNum4);
        Vue.set(this.barData,res.data.weekDataList.weekDate3,res.data.homeWeekOrderData.weekOrderNum3);
        Vue.set(this.barData,res.data.weekDataList.weekDate2,res.data.homeWeekOrderData.weekOrderNum2);
        Vue.set(this.barData,res.data.weekDataList.weekDate1,res.data.homeWeekOrderData.weekOrderNum1);
        Vue.set(this.barData,res.data.weekDataList.weekDate0,res.data.homeWeekOrderData.weekOrderNum0);


        Vue.set(this.weekOrderMoney,res.data.weekDataList.weekDate6,res.data.homeWeekOrderMoneyData.weekOrderNum6);
        Vue.set(this.weekOrderMoney,res.data.weekDataList.weekDate5,res.data.homeWeekOrderMoneyData.weekOrderNum5);
        Vue.set(this.weekOrderMoney,res.data.weekDataList.weekDate4,res.data.homeWeekOrderMoneyData.weekOrderNum4);
        Vue.set(this.weekOrderMoney,res.data.weekDataList.weekDate3,res.data.homeWeekOrderMoneyData.weekOrderNum3);
        Vue.set(this.weekOrderMoney,res.data.weekDataList.weekDate2,res.data.homeWeekOrderMoneyData.weekOrderNum2);
        Vue.set(this.weekOrderMoney,res.data.weekDataList.weekDate1,res.data.homeWeekOrderMoneyData.weekOrderNum1);
        Vue.set(this.weekOrderMoney,res.data.weekDataList.weekDate0,res.data.homeWeekOrderMoneyData.weekOrderNum0);


      })*/

    },


  }


}
</script>

<style lang="less">
.count-style{
  font-size: 50px;
}
</style>
