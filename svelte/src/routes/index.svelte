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

<h1>GetMyDeck</h1>

<form on:submit|preventDefault={handleSubmit}>
  <p>
  <input type="number" name="reservationTime" bind:value={reservationTime}/>
  </p>

  <p>
  <select bind:value={selectedRegion}>
    {#each regions as region}
      <option value={region}>
        {region.text}
      </option>
    {/each}
  </select>
  </p>

  <p>
  <select bind:value={selectedVersion}>
    {#each versions as version}
      <option value={version}>
        {version.text}
      </option>
    {/each}
  </select>
  </p>

  <p>
    <button type=submit>
      Submit
    </button>
  </p>
</form>

<div>
{#if showDeckData }
  <Deckinfo region={selectedRegion.text} version={selectedVersion.value} reservationTime={reservationTime} />
{:else}
  <p class="loading">input not complete...</p>
{/if}
</div>