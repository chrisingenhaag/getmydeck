export interface DeckData {
  personalInfo: PersonalInfo
  officialInfo: OfficialInfo
}

export interface PersonalInfo {
  prettyText: string
  htmlText: string
  historicData: HistoricDeckbotData[]
}

export interface OfficialInfo {
  lastDataUpdate: string
}

export interface HistoricDeckbotData {
  date: string
  elapsedTimePercentage: string
}