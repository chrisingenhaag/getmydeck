<script>
  import Deckinfo from "../components/Deckinfo.svelte";

  let reservationTime;
  let selectedRegion;
  let selectedVersion;
  let showDeckData = false;

  let regions = [
    { id: 0, text: ``, value: undefined },
		{ id: 1, text: `US`, value: `US` },
		{ id: 2, text: `UK`, value: `UK` },
		{ id: 3, text: `EU`, value: `EU` }
	];

  let versions = [
    { id: 0, text: ``, value: undefined },
		{ id: 1, text: `64GB`, value: `64` },
		{ id: 2, text: `256GB`, value: `256` },
		{ id: 3, text: `512GB`, value: `512` }
	];

  function handleSubmit() {
		showDeckData = true;
	}
</script>

<div class="container mx-auto shadow-md p-5 mt-3 w-1/2 bg-white">
  <div class="grid grid-cols-1 gap-6 content-center">
    <div class="block">
      <h1 class="prose prose-lg text-center">How long to get my Steam Deck</h1>
    </div>

    <form on:submit|preventDefault={handleSubmit}>
      <label class="block">
        <span class="text-gray-700">In which region did you preorder your Steam Deck?</span>
        <select class="form-select block rounded-md shadow-sm w-full mt-1" bind:value={selectedRegion}>
          {#each regions as region}
          <option value={region}>
            {region.text}
          </option>
          {/each}
        </select>
      </label>
      <label class="block">
        <span class="text-gray-700">In which region did you preorder your Steam Deck?</span>
        <select class="form-select block rounded-md shadow-sm w-full mt-1" bind:value={selectedVersion}>
          {#each versions as version}
          <option value={version}>
            {version.text}
          </option>
          {/each}
        </select>
      </label>
    
      <label class="block">
        <span class="text-gray-700">Your reservation time</span>
        <input type="number" class="form-input block rounded-md shadow-sm w-full mt-1" name="reservationTime" bind:value={reservationTime}/>
      </label>

      <label class="block">
        <button class="px-4 py-2 mt-5 font-semibold text-sm bg-sky-300 text-white rounded-lg shadow-sm" type=submit>
          Get my current preorder status
        </button>
      </label>
    </form>
    
    <div class="block mt-3 pt-3 border-t-2">
      {#if showDeckData }
      <Deckinfo region={selectedRegion.value} version={selectedVersion.value} reservationTime={reservationTime} />
      {:else}
      <p class="loading">input not complete...</p>
      {/if}
    </div>
  </div>
</div>