<script lang="ts">
  import { onMount } from 'svelte';

  import type { Region, Version } from '$lib/DeckTypes';
  import Description from '$lib/Description.svelte';
  import Changelog from '$lib/Changelog.svelte';
  import Footer from '$lib/Footer.svelte';

  const REMEMBERME_KEY = "urn:getmydeck:rememberme";

  let reservationTime: string;
  let selectedRegion: Region
  let selectedVersion: Version

  let rememberme = false;

  let showValidationError = false;

  let regions = [
    { id: 0, text: `empty`, value: undefined },
		{ id: 1, text: `US`, value: `US` },
		{ id: 2, text: `UK`, value: `UK` },
		{ id: 3, text: `EU`, value: `EU` }
	];

  let versions = [
    { id: 0, text: `empty`, value: undefined },
		{ id: 1, text: `64GB`, value: `64` },
		{ id: 2, text: `256GB`, value: `256` },
		{ id: 3, text: `512GB`, value: `512` }
	];

  function handleSubmit() {
    if(selectedRegion !== undefined && selectedVersion !== undefined && (reservationTime !== undefined && reservationTime !== null)) {
      
      const resultLink = `s/${selectedRegion}/${selectedVersion}/${reservationTime}`

      if(rememberme === true) {
        saveRememberme()
      } else {
        localStorage.clear();
      }

      window.location.assign(resultLink)
    } else {
      showValidationError = true;
    }
	}

  let saveRememberme = () => {
    let valueToStore = {
      region: selectedRegion,
      version: selectedVersion,
      timestamp: reservationTime
    }
    localStorage.setItem(REMEMBERME_KEY, JSON.stringify(valueToStore));
  }

  onMount(async () => {
    let storedString = localStorage.getItem(REMEMBERME_KEY);
    if (storedString !== null) {
      let storedValues = JSON.parse(storedString)
      reservationTime = storedValues.timestamp
      selectedRegion = storedValues.region
      selectedVersion = storedValues.version
      rememberme = true
    }
  });
</script>

<svelte:head>
  <title>GetMyDeck - Calculator</title>
</svelte:head>

<div class="container mx-auto shadow-md p-5 mt-3 md:w-1/2 bg-white prose">
  <div class="grid grid-cols-1 gap-6 content-center">
    
    <Description />

    <form on:submit|preventDefault={handleSubmit}>
      <div class="">
        <label for="region" class="text-gray-700">In which region did you preorder your Steam Deck?</label>
        <select class="form-select block rounded-md shadow-sm w-full mt-1" id="region" name="region" bind:value={selectedRegion}>
          {#each regions as region}
          <option label={region.text} value={region.value}>
            {region.text}
          </option>
          {/each}
        </select>
      </div>
      <div class="">
        <label for="version" class="text-gray-700">Which version did you reserve?</label>
        <select class="form-select block rounded-md shadow-sm w-full mt-1" id="version" name="version" bind:value={selectedVersion}>
          {#each versions as version}
          <option label={version.text} value={version.value}>
            {version.text}
          </option>
          {/each}
        </select>
      </div>
    
      <div class="">
        <label for="reserationTime" class="text-gray-700">Your reservation time (in seconds from 01.01.1970 example: 1627022437). Get it like 
          described in the <a target="_blank" href="https://www.reddit.com/r/SteamDeck/comments/ui642q/introducing_deckbot/">reddit DeckBot description</a>.
        </label>
        <input type="number" class="form-input block rounded-md shadow-sm w-full mt-1" name="reservationTime" id="reserationTime" bind:value={reservationTime}/>
      </div>

      <label class="">
        <button class="px-4 py-2 mt-5 font-semibold text-sm bg-sky-900 active:bg-sky-600 text-white rounded-lg shadow-sm" type="submit">
          Get my current preorder status
        </button>
      </label>

      <label class="block mt-3">
        <input type="checkbox" class="form-input rounded-md shadow-sm" name="rememberme" id="rememberme" bind:checked={rememberme}/>
        <span class="text-gray-700">Remember me</span>
      </label>

      {#if showValidationError }
      <p>Please fill out form completely</p>
      {/if}
    </form>
    
    <Changelog />

    <Footer />
  </div>
</div>