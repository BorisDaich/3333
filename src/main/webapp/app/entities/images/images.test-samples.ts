import { IImages, NewImages } from './images.model';

export const sampleWithRequiredData: IImages = {
  id: 'e9113fff-5d9e-4ced-9639-ff550dea43c0',
};

export const sampleWithPartialData: IImages = {
  id: 'd2538526-3b4a-4d7e-bf05-6c9eff5a864b',
  pngContent: '../fake-data/blob/hipster.png',
  pngContentContentType: 'unknown',
  rawWidth: 13323,
  rawHeight: 1519,
  rawFormat: '../fake-data/blob/hipster.txt',
  rawContent: '../fake-data/blob/hipster.png',
  rawContentContentType: 'unknown',
};

export const sampleWithFullData: IImages = {
  id: '32f6488e-e482-47a9-9699-0ac20288a098',
  pngContent: '../fake-data/blob/hipster.png',
  pngContentContentType: 'unknown',
  rawWidth: 19855,
  rawHeight: 28825,
  rawFormat: '../fake-data/blob/hipster.txt',
  rawContent: '../fake-data/blob/hipster.png',
  rawContentContentType: 'unknown',
};

export const sampleWithNewData: NewImages = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
