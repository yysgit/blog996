<template>
    <div ref="dom"></div>
</template>

<script>
import echarts from 'echarts'
import { on, off } from '@/libs/tools'
export default {
  name: 'serviceRequests',
  props: {
    value: Object,
    text: String,
    subtext: String
  },
  data () {
    return {
      dom: null,
      option:{},
    }
  },
  methods: {
    resize () {
      this.dom.resize()
    }
  },
  mounted () {
    let xAxisData = Object.keys(this.value)
    let seriesData = Object.values(this.value)

    this.option = {

      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross',
          label: {
            backgroundColor: '#6a7985'
          }
        }
      },
      grid: {
        top: '3%',
        left: '1.2%',
        right: '3%',
        bottom: '3%',
        containLabel: true
      },
      xAxis: [
        {
          type: 'category',
          boundaryGap: false,
          data: xAxisData
        }
      ],
      yAxis: [
        {
          type: 'value'
        }
      ],
      series: [
        /*{
          name: '运营商/网络服务',
          type: 'line',
          stack: '总量',
          areaStyle: { normal: {
            color: '#2d8cf0'
          } },
          data: [120, 132, 101, 134, 90, 230, 210]
        },
        {
          name: '银行/证券',
          type: 'line',
          stack: '总量',
          areaStyle: { normal: {
            color: '#10A6FF'
          } },
          data: [257, 358, 278, 234, 290, 330, 310]
        },
        {
          name: '游戏/视频',
          type: 'line',
          stack: '总量',
          areaStyle: { normal: {
            color: '#0C17A6'
          } },
          data: [379, 268, 354, 269, 310, 478, 358]
        },
        {
          name: '餐饮/外卖',
          type: 'line',
          stack: '总量',
          areaStyle: { normal: {
            color: '#4608A6'
          } },
          data: [320, 332, 301, 334, 390, 330, 320]
        },
        {
          name: '快递/电商',
          type: 'line',
          stack: '总量',
          label: {
            normal: {
              show: true,
              position: 'top'
            }
          },
          areaStyle: { normal: {
            color: '#398DBF'
          } },
          data: [820, 645, 546, 745, 872, 624, 258]
          },*/
     {
         name: '订单金额',
         type: 'line',
         stack: '总量',
         label: {
           normal: {
             show: true,
             position: 'top'
           }
         },
         areaStyle: {
           normal: {
           color: '#398DBF'
         }
         },

         itemStyle : {
           normal : {
             color:'#8cd5c2', //改变折线点的颜色
             lineStyle:{
               color:'#8cd5c2' //改变折线颜色
             }
           }
         },
         data:seriesData
        }
      ]
    }
    this.$nextTick(() => {
      this.dom = echarts.init(this.$refs.dom)
      this.dom.setOption(this.option);
      on(window, 'resize', this.resize)
    })
  },
  beforeDestroy () {
    off(window, 'resize', this.resize)
  },
  watch: {
    //观察data的变化
    value: {
      handler(newVal, oldVal) {
        let xAxisData = Object.keys(this.value);
        let seriesData = Object.values(this.value);

        console.log(xAxisData);
        console.log(seriesData);

        for(var i=0;i<seriesData.length;i++){
          console.log(seriesData);
          console.log(seriesData[i]==null);
          if(seriesData[i]==null){
            seriesData[i]=0;
          }
        }

        if(this.dom){
          if(newVal){
            this.option.xAxis[0].data = xAxisData;
            this.option.series[0].data = seriesData;
            this.dom.setOption(this.option);
          }
        }
        else {
          echarts.init()
        }
      },
      deep: true //对象内部属性的监听，关键。
    }
  },
}
</script>
