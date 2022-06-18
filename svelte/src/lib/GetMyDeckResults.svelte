<script lang="ts">
  import DeckChart from '$lib/DeckChart.svelte'

  import type { DeckData, Region, Version } from '$lib/DeckTypes'
  
  export let region: Region
  export let version: Version
  export let timestamp: string

  let deckdata: DeckData

  let deckdataLastUpdatedString: string
  let errorMessage: string

  let fetchDeckInfos = async (re: Region, ver: Version, rt: string) => {
    errorMessage = ''
    await fetch(`/api/v2/regions/${re}/versions/${ver}/infos/${rt}`)
      .then(r => r.json())
      .then(data => {
        deckdata = data

        let date = new Date(deckdata.officialInfo.lastDataUpdate)
        deckdataLastUpdatedString = date.toLocaleString()
      })
      .catch(() => {
        errorMessage = "Problem loading infos."
      })
    }

  $: {  
    fetchDeckInfos(region, version, timestamp)
  }
</script>

{#if errorMessage}
  <p>{errorMessage}</p>
{:else}
  {#if deckdata}
    {@html deckdata.personalInfo.htmlText}
    <h4>Past percentages</h4>
    <DeckChart historicData={deckdata.personalInfo.historicData} />
    <p class="text-xs">
      Data last updated from deckbot sheet by <a target="_blank" href="https://www.reddit.com/u/Fammy">u/Fammy</a>: {deckdataLastUpdatedString}
    </p>
  {:else}
    <p>Fetching infos ...</p>
  {/if}
{/if}