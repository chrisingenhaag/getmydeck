<script lang="ts">
  import type { DeckData } from 'src/types/DeckTypes';

  import { onMount } from 'svelte';

  const REMEMBERME_KEY = "urn:getmydeck:rememberme";
  
  let reservationTime: string;
  let selectedRegion: string;
  let selectedVersion: string;
  let rememberme = false;
  let showDeckData = false
  let deckdata: DeckData;
  let showValidationError = false;
  let errorMessage: string;

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
    if(selectedRegion !== undefined && selectedVersion !== undefined && (reservationTime !== undefined && reservationTime !== null)) {
      fetchDeckInfos(selectedRegion, selectedVersion, reservationTime)
      showValidationError = false;
      showDeckData = true;
      console.log(rememberme);
      if(rememberme === true) {
        saveRememberme()
      } else {
        localStorage.clear();
      }
    } else {
      showValidationError = true;
      showDeckData = false
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

  let fetchDeckInfos = async (re: string, ver: string, rt: string) => {
    errorMessage = '';
    await fetch(`/api/v2/regions/${re}/versions/${ver}/infos/${rt}`)
      .then(r => r.json())
      .then(data => {
        deckdata = data;
      })
      .catch(() => {
        errorMessage = "Problem loading infos. Please fix your inputs."
      })
      .finally(() => {
        showDeckData = true;
      });
    };

  onMount(async () => {
    let storedString = localStorage.getItem(REMEMBERME_KEY);
    if (storedString !== null) {
      let storedValues = JSON.parse(storedString);
      reservationTime = storedValues.timestamp;
      selectedRegion = storedValues.region;
      selectedVersion = storedValues.version;
      rememberme = true;
      fetchDeckInfos(selectedRegion, selectedVersion, reservationTime);
    }
  });
</script>

<div class="container mx-auto shadow-md p-5 mt-3 md:w-1/2 bg-white prose">
  <div class="grid grid-cols-1 gap-6 content-center">
    <article class="">
      <h1 class="text-center">How long to get my Steam Deck?</h1>
      <p>Inspired from the reddit Steam Deck Order Email Megathreads <a target="_blank" href="https://www.reddit.com/r/SteamDeck/search?q=flair_name%3A%22MEGATHREAD%22&restrict_sr=1">(list of them here)</a> 
        I wanted to give an alternative way of getting actual information
        besides talking to the deckbot within the reddit. 
      </p>
      <p>
        Anyway thanks to the great work of all guys behind the thread and their work on organizing this 
        and making the data available. 
        This site also relies on the data the people share in the reddit to 
        collect information which people are able to
        order their steam deck based on the reservation time, their region and their version.
      </p>
      <p>
        Here you just need to enter your data to get your information about how far 
        away you might be from ordering your steam deck. Every week 
        you can just come back and see if something has changed (without a reddit 
        account and talking to deckbot every week).
      </p>
    </article>

    <form on:submit|preventDefault={handleSubmit}>
      <label class="">
        <span class="text-gray-700">In which region did you preorder your Steam Deck?</span>
        <select class="form-select block rounded-md shadow-sm w-full mt-1" id="region" name="region" bind:value={selectedRegion}>
          {#each regions as region}
          <option value={region.value}>
            {region.text}
          </option>
          {/each}
        </select>
      </label>
      <label class="">
        <span class="text-gray-700">Which version did you reserve?</span>
        <select class="form-select block rounded-md shadow-sm w-full mt-1" id="version" name="version" bind:value={selectedVersion}>
          {#each versions as version}
          <option value={version.value}>
            {version.text}
          </option>
          {/each}
        </select>
      </label>
    
      <label class="">
        <span class="text-gray-700">Your reservation time (in seconds from 01.01.1970 example: 1627022437). Get it like 
          described in the DeckBot description <a target="_blank" href="https://www.reddit.com/r/SteamDeck/comments/ui642q/introducing_deckbot/">here</a>
        </span>
        <input type="number" class="form-input block rounded-md shadow-sm w-full mt-1" name="reservationTime" id="reserationTime" bind:value={reservationTime}/>
      </label>

      <label class="">
        <button class="px-4 py-2 mt-5 font-semibold text-sm bg-sky-300 active:bg-sky-500 text-white rounded-lg shadow-sm" type="submit">
          Get my current preorder status
        </button>
      </label>

      <label class="block mt-3">
        <input type="checkbox" class="form-input rounded-md shadow-sm" name="rememberme" id="rememberme" bind:checked={rememberme}/>
        <span class="text-gray-700">Remember me</span>
      </label>
    </form>
    
    <div class="block border-t-2">
      <h3>Results:</h3>
      {#if showValidationError }
      <p>Please fill out form completely</p>
      {/if}
      {#if showDeckData }
        {#if errorMessage}
          <p>{errorMessage}</p>
        {:else}
          {#if deckdata}
            <p>{deckdata.personalInfo.prettyText}</p>
            <p class="text-xs">
              Data last fetched from deckbot sheet: {deckdata.officialInfo.lastDataUpdate}
            </p>
          {:else}
            <p>Fetching infos ...</p>
          {/if}
        {/if}      
      {/if}
    </div>
    <div class="block border-t-2 text-xs">
      <h4>Changelog:</h4>
      <ul>
        <li>May 30, 2022: Updated to auto fetch data from deckbot google sheet</li>
        <li>May 30, 2022: Updated data from deckbot</li>
        <li>May 29, 2022: Added reservation timestamp from last order of your specific version of Steam Deck in form response</li>
        <li>May 26, 2022: Updated data from deckbot googlesheet</li>
      </ul>

      <h4>Todo:</h4>
      <ul>
        <li class="line-through">Auto update data from deckbot datasource</li>
      </ul>
    </div>
    <div class="block border-t-2">
      <p>If you have questions or comments feel free to reach out for me on reddit <a target="_blank" href="https://www.reddit.com/u/Labidou51">Labidou51</a></p>
    </div>
  </div>
</div>