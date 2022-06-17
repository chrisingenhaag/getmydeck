export type Version = '64' | '256' | '512'
export type Region = 'EU' | 'UK' | 'US'

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
  elapsedTimePercentage: number
}

export interface ChartData {
  labels: string[]
  datasets: ChartDataSet[]
  yMarkers?: YMarker[]
}

export interface ChartDataSet {
  values: number[]
}

export interface YMarker {
  label: string
  value: number
  type: string
}