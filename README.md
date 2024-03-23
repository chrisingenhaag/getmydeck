# GetMyDeck

![Build](https://github.com/chrisingenhaag/getmydeck/actions/workflows/gradle-build.yml/badge.svg?branch=main)

Spring-Boot based api backend for [getmydeck.ingenhaag.dev](https://getmydeck.ingenhaag.dev)

In 2022 getmydeck was a tool for the [reddit community](https://www.reddit.com/search/?q=getmydeck&type=link&sort=new) around the release of the Valve Steam Deck. Based on crowdfunded data from
the community users were able to get information about Valves shipment progress. During that time Valve sent out emails
twice a week and notified some of the customers that they are now able to proceed to order. This data was shared by the reddit users and scraped
by getmydeck. Everyone else hammered F5 in their browser twice a week to see how much progress Valve did, sample result below.

![getmydeck sample restul](https://github.com/chrisingenhaag/getmydeck-app/blob/main/doc/sample-result.png?raw=true)

## :warning: Deprecation notice

As of october 2022, this api went to status read only as no more new data had to be scraped. Then the api stayed readonly for about a year, and from now on the api is not reachable anymore as
[getmydeck-app](https://github.com/chrisingenhaag/getmydeck-app) catches the api route and redirects to the [all queue statistics](https://getmydeck.ingenhaag.dev/statistics).
The frontend holds the last static copy of the data for all queues.


## build and deploy

```bash
./gradlew bootBuildImage
```

## General infos

Google sheet from u/fammy https://docs.google.com/spreadsheets/d/1ZaKncig9fce7K0sr1f-E2_sgLH1HuKQ-q3k7clPMOCs/edit?usp=sharing

### Expected sample output

Request:

```bash
https getmydeck.ingenhaag.dev/api/v2/regions/EU/versions/512/infos/1627022437
```

Response:

```json
{
  "officialInfo": {
    "lastDataUpdate": "2022-06-25T13:30:50Z",
    "lastDataUpdateDate": "2022-06-25",
    "lastShipments": {
      "EU": {
        "256": "2021-07-23T11:10:02Z",
        "512": "2021-07-23T18:14:54Z",
        "64": "2021-07-22T23:45:13Z"
      },
      "UK": {
        "256": "2021-07-16T19:31:59Z",
        "512": "2021-07-16T17:41:33Z",
        "64": "2021-07-20T18:39:44Z"
      },
      "US": {
        "256": "2021-07-16T23:10:46Z",
        "512": "2021-07-16T18:12:15Z",
        "64": "2021-07-17T00:24:42Z"
      }
    },
    "reservationsStartedAt": "2021-07-16T17:00:00Z"
  },
  "personalInfo": {
    "durationReservedAfterStart": "PT157H40M37S",
    "durationReservedAfterStartHumanReadable": "06 days, 13 hours, 40 minutes and 37 seconds",
    "elapsedTimePercentage": 107.33,
    "historicData": [
      {
        "date": "2022-06-23",
        "elapsedTimePercentage": 107.33
      },
      {
        "date": "2022-06-20",
        "elapsedTimePercentage": 78.3
      },
      {
        "date": "2022-06-16",
        "elapsedTimePercentage": 61.72
      },
      {
        "date": "2022-06-13",
        "elapsedTimePercentage": 49.42
      },
      {
        "date": "2022-06-09",
        "elapsedTimePercentage": 44.09
      },
      {
        "date": "2022-06-06",
        "elapsedTimePercentage": 38.33
      },
      {
        "date": "2022-06-02",
        "elapsedTimePercentage": 30.6
      },
      {
        "date": "2022-05-30",
        "elapsedTimePercentage": 26.86
      },
      {
        "date": "2022-05-26",
        "elapsedTimePercentage": 20.7
      },
      {
        "date": "2022-05-23",
        "elapsedTimePercentage": 16.48
      },
      {
        "date": "2022-05-19",
        "elapsedTimePercentage": 14.05
      },
      {
        "date": "2022-05-16",
        "elapsedTimePercentage": 11.97
      },
      {
        "date": "2022-05-12",
        "elapsedTimePercentage": 10.45
      },
      {
        "date": "2022-05-09",
        "elapsedTimePercentage": 9.25
      },
      {
        "date": "2022-05-05",
        "elapsedTimePercentage": 6.64
      },
      {
        "date": "2022-05-02",
        "elapsedTimePercentage": 3.54
      },
      {
        "date": "2022-04-28",
        "elapsedTimePercentage": 2.78
      },
      {
        "date": "2022-04-25",
        "elapsedTimePercentage": 2.27
      },
      {
        "date": "2022-04-21",
        "elapsedTimePercentage": 1.93
      },
      {
        "date": "2022-04-18",
        "elapsedTimePercentage": 1.68
      },
      {
        "date": "2022-04-14",
        "elapsedTimePercentage": 1.41
      },
      {
        "date": "2022-04-11",
        "elapsedTimePercentage": 1.01
      },
      {
        "date": "2022-04-07",
        "elapsedTimePercentage": 0.8
      }
    ],
    "htmlText": "<ul>\n  <li>It looks like you have a EU 512GB reservation</li>\n  <li>You reserved your deck 06 days, 13 hours, 40 minutes and 37 seconds after pre-orders opened</li>\n  <li>07 days, 01 hours, 14 minutes and 54 seconds of orders have been processed</li>\n  <li>You're 107.33 % of the way there</li>\n  <li>Apparently someone was already able to order with a later reservation time than yours</li>\n  <li>You should have received your mail from valve!</li>\n</ul>",
    "latestOrder": "2021-07-23T18:14:54Z",
    "latestOrderSeconds": 1627064094,
    "prettyText": "It looks like you have a EU 512GB reservation.\nYou reserved your deck 06 days, 13 hours, 40 minutes and 37 seconds after pre-orders opened.\n07 days, 01 hours, 14 minutes and 54 seconds of orders have been processed. This is 107.33 % so you should have received your order information from valve.\nCheck your mail and spam folder!",
    "region": "EU",
    "reservedAt": "2021-07-23T06:40:37Z",
    "version": "512"
  }
}
```
