<script lang="ts">
  import type { ChartData, HistoricDeckbotData, YMarker } from '$lib/DeckTypes';

  import Chart from 'svelte-frappe-charts'
  
  export let historicData: HistoricDeckbotData[]
  
  let chartData: ChartData = {
    labels: [],
    datasets: [],
  };

  const yMarkers: YMarker[] = [
    {
      label: '',
      value: 0,
      type: 'solid'
    },
    {
      label: '',
      value: 100,
      type: 'solid'
    }
  ]

  let chartLineOptions = {
    regionFill: 1 // default: 0
  }

  let chartTooltipOptions = {
    formatTooltipY: (d: number) => d + ' %',
    formatTooltipX: (d: string) => 'date: '+ d
  }




  let values: number[] = []
  let labels: string[] = []
  const datacopy: HistoricDeckbotData[] = []
  
  historicData.forEach(val => datacopy.push(Object.assign({}, val)));
  datacopy.reverse().forEach((item) => {
    const monthDay = item.date.split('-')
    labels.push(monthDay[1]+'-'+monthDay[2]);
    values.push(item.elapsedTimePercentage)
  })
  chartData.labels = labels
  chartData.datasets = [
    {
      values: values
    }
  ]

</script>

{#if chartData}
  <Chart data={chartData} type="line" 
      lineOptions={chartLineOptions} 
      tooltipOptions={chartTooltipOptions} 
      valuesOverPoints="1"/>
{/if}

