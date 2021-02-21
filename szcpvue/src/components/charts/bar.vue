<template>
  <div ref="dom" class="charts chart-bar"></div>
</template>

<script>
import echarts from 'echarts'
import tdTheme from './theme.json'
import { on, off } from '@/libs/tools'
echarts.registerTheme('tdTheme', tdTheme)
export default {
  name: 'ChartBar',
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
    this.$nextTick(() => {
      let xAxisData = Object.keys(this.value)
      let seriesData = Object.values(this.value)
      this.option = {
        title: {
          text: this.text,
          subtext: this.subtext,
          x: 'center'
        },
        xAxis: {
          type: 'category',
          data: xAxisData
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: seriesData,
          type: 'bar'
        }]
      }
      this.dom = echarts.init(this.$refs.dom, 'tdTheme')
      this.dom.setOption(this.option)
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

        if(this.dom){
          if(newVal){
            this.option.xAxis.data = xAxisData;
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
