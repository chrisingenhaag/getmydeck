export interface DeckData {
  personalInfo: PersonalInfo
  officialInfo: OfficialInfo
}

export interface PersonalInfo {
  prettyText: string
  htmlText: string
}

export interface OfficialInfo {
  lastDataUpdate: string
}