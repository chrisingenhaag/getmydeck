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

  let toggleShowAllData = () => {
    showAllData = !showAllData
  }
  let toggleFullYScale = () => {
    showFullYScale = !showFullYScale
  }

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
  <div class="object-right">
    <span>
      Show all data
      <label class="switch">
        <input type="checkbox" on:click={toggleShowAllData} class="w-0 h-0 opacity-0">
        <span class="slider round rounded-2xl"></span>
      </label>
    </span>
    <span>
      / 
      Full % scale
      <label class="switch">
        <input type="checkbox" on:click={toggleFullYScale} class="w-0 h-0 opacity-0">
        <span class="slider round rounded-2xl"></span>
      </label>
    </span>
  </div>
{/if}

<style>
  /* The switch - the box around the slider */
  .switch {
    position: relative;
    display: inline-block;
    width: 30px;
    height: 17px;
  }

  /* The slider */
  .slider {
    position: absolute;
    cursor: pointer;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: #ccc;
    -webkit-transition: .4s;
    transition: .4s;
  }

  .slider:before {
    position: absolute;
    content: "";
    height: 13px;
    width: 13px;
    left: 2px;
    bottom: 2px;
    background-color: white;
    -webkit-transition: .4s;
    transition: .4s;
  }

  input:checked + .slider {
    background-color: #2196F3;
  }

  input:focus + .slider {
    box-shadow: 0 0 1px #2196F3;
  }

  input:checked + .slider:before {
    -webkit-transform: translateX(13px);
    -ms-transform: translateX(13px);
    transform: translateX(13px);
  }

  .slider.round:before {
    border-radius: 50%;
  } 
</style>

