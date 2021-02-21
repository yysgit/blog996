<template>
  <div ref="dom" class="charts chart-pie"></div>
</template>

<script>
  import echarts from 'echarts'
  import tdTheme from './theme.json'
  import {on, off} from '@/libs/tools'

  echarts.registerTheme('tdTheme', tdTheme)
  export default {
    name: 'ChartPie',
    props: {
      value: Array,
      text: String,
      subtext: String
    },
    data() {
      return {
        dom: null,
        option:{},
      }
    },
    methods: {
      resize() {
        this.dom.resize()
      }
    },
    mounted() {
      this.$nextTick(() => {
        let legend = this.value.map(_ => _.name)
        this.option = {
          title: {
            text: this.text,
            subtext: this.subtext,
            x: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c} ({d}%)'
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            data: legend
          },
          series: [
            {
              type: 'pie',
              radius: '55%',
              center: ['50%', '60%'],
              data: this.value,
              itemStyle: {
                emphasis: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        }
        this.dom = echarts.init(this.$refs.dom, 'tdTheme')
        this.dom.setOption(this.option)
        on(window, 'resize', this.resize)
      })
    },
    beforeDestroy() {
      off(window, 'resize', this.resize)
    },
    watch: {
      //观察data的变化
      value: {
        handler(newVal, oldVal) {

          if(this.dom){
            if(newVal){
              this.option.series.data = newVal;
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
