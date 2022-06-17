<script context="module">
	export async function load({ params }) {
    const {region, version, timestamp} = params

		return {
			props: { region, version, timestamp }
		};
	}
</script>

<script lang="ts">
  import type { Region, Version } from '$lib/DeckTypes';

  import GetMyDeckResults from '$lib/GetMyDeckResults.svelte'

  export let region: Region
  export let version: Version
  export let timestamp: string

</script>


<svelte:head>
  <title>GetMyDeck - Shared Progress</title>
</svelte:head>

<div class="container mx-auto shadow-md p-5 mt-3 md:w-1/2 bg-white prose">
  <div class="grid grid-cols-1 gap-6 content-center">
    <article class="">
      <h1 class="text-center">How long to get my Steam Deck?</h1>

      <h3>Results:</h3>

      <GetMyDeckResults region={region} version={version} timestamp={timestamp} />

    </article>
  </div>
</div>

