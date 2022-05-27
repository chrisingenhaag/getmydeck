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
  <p>{region}</p>

  {#if deckdata}
    <p>{deckdata}</p>
  {:else}
    <p class="loading">loading...</p>
  {/if}
</div>