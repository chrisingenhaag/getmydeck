<script>
  import Deckinfo from "../components/Deckinfo.svelte";

  let reservationTime;
  let selectedRegion;
  let selectedVersion;
  let showDeckData = false;

  let regions = [
		{ id: 1, text: `US` },
		{ id: 2, text: `UK` },
		{ id: 3, text: `EU` }
	];

  let versions = [
		{ id: 1, text: `64GB`, value: `64` },
		{ id: 2, text: `256GB`, value: `256` },
		{ id: 3, text: `512GB`, value: `512` }
	];

  function handleSubmit() {
		showDeckData = true;
	}
</script>

<div class="container mx-auto shadow-md p-5 mt-3 w-1/2">
  <div class="flex flex-row">
    <h1>GetMyDeck</h1>
  </div>
  
  <form on:submit|preventDefault={handleSubmit}>
    
    <div class="flex flex-row">
      <select bind:value={selectedRegion}>
        {#each regions as region}
        <option value={region}>
          {region.text}
        </option>
        {/each}
      </select>
    </div>
    
    <div class="flex flex-row">
      <select bind:value={selectedVersion}>
        {#each versions as version}
        <option value={version}>
          {version.text}
        </option>
        {/each}
      </select>
    </div>
    
    <div class="flex flex-row">
      <input type="number" name="reservationTime" bind:value={reservationTime}/>
    </div>
    
    <div class="flex flex-row">
      <button type=submit>
        Submit
      </button>
    </div>
  </form>
  
  <div>
    {#if showDeckData }
    <Deckinfo region={selectedRegion.text} version={selectedVersion.value} reservationTime={reservationTime} />
    {:else}
    <p class="loading">input not complete...</p>
    {/if}
  </div>
</div>