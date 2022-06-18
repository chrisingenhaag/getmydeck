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

  let showAllData: boolean = false
  let showFullYScale: boolean = false

  $: {
    let values: number[] = []
    let labels: string[] = []
    const datacopy: HistoricDeckbotData[] = []
    
    const tempArray = historicData.slice(0, showAllData ? historicData.length-1 : 7)
    tempArray.forEach(val => datacopy.push(Object.assign({}, val)));

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
    if(showFullYScale) {
      chartData.yMarkers = yMarkers
    } else {
      chartData.yMarkers = undefined
    }
  }


</script>

{#if chartData}
  <Chart data={chartData} type="line" 
    lineOptions={chartLineOptions} 
    tooltipOptions={chartTooltipOptions} 
    valuesOverPoints="1"/>
  <div class="flex flex-row">
    <div class="basis-1/2">
      <label for="showAllData" class="flex items-center cursor-pointer relative">
        <input bind:checked={showAllData} type="checkbox" id="showAllData" class="sr-only">
        <div class="toggle-bg bg-gray-200 border-2 border-gray-200 h-6 w-11 rounded-full"></div>
        <span class="ml-3 text-gray-900 text-sm font-medium">Show all data</span>
      </label>
    </div>
    <div class="basis-1/2">
      <label for="showFullYScale" class="flex items-center cursor-pointer relative">
        <input bind:checked={showFullYScale} type="checkbox" id="showFullYScale" class="sr-only">
        <div class="toggle-bg bg-gray-200 border-2 border-gray-200 h-6 w-11 rounded-full"></div>
        <span class="ml-3 text-gray-900 text-sm font-medium">Full % scale</span>
      </label>
    </div>
  </div>
{/if}

<style>
  .toggle-bg:after {
    content: '';
    @apply absolute top-0.5 left-0.5 bg-white border border-gray-300 rounded-full h-5 w-5 transition shadow-sm;
  }
  input:checked + .toggle-bg:after {
    transform: translateX(100%);
    @apply border-white;
  }
  input:checked + .toggle-bg {
    @apply bg-blue-600 border-blue-600;
  }
</style>

