<script context="module">
	export function load({ error, status }) {
		return {
			props: { error, status }
		};
	}
</script>

<script>
	import { dev } from '$app/env';
	export let error, status;
</script>

<svelte:head>
  <title>{status}</title>
</svelte:head>

<div class="container mx-auto shadow-md p-5 mt-3 md:w-1/2 bg-white prose">
  <div class="grid grid-cols-1 gap-6 content-center">
    <article class="">
      <h1 class="text-center">How long to get my Steam Deck?</h1>

      <p>Oops, this page does not exist</p>

      <a href="/">Back to Home</a>

      {#if dev && error.stack}
        <pre>{error.stack}</pre>
      {/if}
    </article>
  </div>
</div>
