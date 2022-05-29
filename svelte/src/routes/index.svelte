<script>
  let reservationTime;
  let selectedRegion;
  let selectedVersion;
  let showDeckData = false
  let deckdata;
  let showValidationError = false;
  let errorMessage;

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
    if(selectedRegion.value !== undefined && selectedVersion.value !== undefined && (reservationTime !== undefined && reservationTime !== null)) {
      fetchDeckInfos(selectedRegion.value, selectedVersion.value, reservationTime)
      showValidationError = false;
      showDeckData = true;
    } else {
      showValidationError = true;
      showDeckData = false
    }
	}

  let fetchDeckInfos = async (re, ver, rt) => {
    errorMessage = undefined;
    await fetch(`/api/v2/regions/${re}/versions/${ver}/infos/${rt}`)
      .then(r => r.json())
      .then(data => {
        deckdata = data;
        showDeckData = true;
      })
      .catch(() => {
        errorMessage = "Problem loading infos. Please fix your inputs."
      });
    };
</script>

<div class="container mx-auto shadow-md p-5 mt-3 md:w-1/2 bg-white">
  <div class="grid grid-cols-1 gap-6 content-center">
    <div class="block prose-lg text-center">
      <h1>How long to get my Steam Deck</h1>
    </div>

    <form on:submit|preventDefault={handleSubmit}>
      <label class="block">
        <span class="text-gray-700">In which region did you preorder your Steam Deck?</span>
        <select class="form-select block rounded-md shadow-sm w-full mt-1" id="region" name="region" bind:value={selectedRegion}>
          {#each regions as region}
          <option value={region}>
            {region.text}
          </option>
          {/each}
        </select>
      </label>
      <label class="block">
        <span class="text-gray-700">In which region did you preorder your Steam Deck?</span>
        <select class="form-select block rounded-md shadow-sm w-full mt-1" id="version" name="version" bind:value={selectedVersion}>
          {#each versions as version}
          <option value={version}>
            {version.text}
          </option>
          {/each}
        </select>
      </label>
    
      <label class="block">
        <span class="text-gray-700">Your reservation time (in seconds from 01.01.1970 example: 1627022437)</span>
        <input type="number" class="form-input block rounded-md shadow-sm w-full mt-1" name="reservationTime" id="reserationTime" bind:value={reservationTime}/>
      </label>

      <label class="block">
        <button class="px-4 py-2 mt-5 font-semibold text-sm bg-sky-300 active:bg-sky-500 text-white rounded-lg shadow-sm" type=submit>
          Get my current preorder status
        </button>
      </label>
    </form>
    
    <div class="block mt-3 pt-3 border-t-2 prose-lg">
      {#if showValidationError }
      <p>Please fill out form completely</p>
      {/if}
      {#if showDeckData }
        {#if errorMessage}
          <p>{errorMessage}</p>
        {:else}
          {#if deckdata}
            <p>{deckdata.personalInfo.prettyText}</p>
          {:else}
            <p>fetching infos ...</p>
          {/if}
        {/if}
      {/if}
    </div>
  </div>
</div>