export interface EtablissementInterface {
    id?: string,
    nom: string,
    numeroTelephone: string,
    adresseMail: string,
    rue: string,
    codePostal: string | '00000',
    ville: string,
    pays: string | 'FRANCE',
    nbPlaces: number | 0,
    nbUnitesOff: number | 0
}

export interface UniteInterface {
    id?: string,
    idEtablissement: string,
    nom: string,
    etat: UniteEtatEnum | UniteEtatEnum.Ouverte
}

export enum UniteEtatEnum {
    Ouverte = 'OUVERTE',
    Fermee = 'FERMEE',
    Contagieuse = 'CONTAGIEUSE'
}