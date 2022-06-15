export interface DeckData {
  personalInfo: PersonalInfo
  officialInfo: OfficialInfo
}

export interface PersonalInfo {
  prettyText: string
  htmlText: string
  elapsedTimePercentage: number
  historicData: HistoricDeckbotData[]
}

export interface OfficialInfo {
  lastDataUpdate: string
  lastDataUpdateDate: string
}

export interface HistoricDeckbotData {
  date: string
  elapsedTimePercentage: string
}