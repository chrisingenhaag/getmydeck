<script>
  import { onMount } from "svelte";

  // export creates comp props
  export let region;
  export let version;
  export let reservationTime;


  // define the data holding variable
  let deckdata;

  onMount(async () => {
    await fetch(`/api/v2/regions/${region}/versions/${version}/infos/${reservationTime}`)
      .then(r => r.json())
      .then(data => {
        deckdata = data;
      });
  })

</script>

<div>
  {#if deckdata}
    <p class="prose">{deckdata.personalInfo.prettyText}</p>
  {:else}
    <p class="prose">loading...</p>
  {/if}
</div>