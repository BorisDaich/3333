import dayjs from 'dayjs/esm';

import { ICatalogs, NewCatalogs } from './catalogs.model';

export const sampleWithRequiredData: ICatalogs = {
  id: '9a700475-5c82-45ae-9051-6d9ebf889d3a',
  externalId: '../fake-data/blob/hipster.txt',
  name: '../fake-data/blob/hipster.txt',
  isActive: false,
};

export const sampleWithPartialData: ICatalogs = {
  id: 'b62cd286-6558-4a94-934f-646220e0e1e9',
  externalId: '../fake-data/blob/hipster.txt',
  name: '../fake-data/blob/hipster.txt',
  isActive: false,
  createdTime: dayjs('2023-07-17T12:40'),
};

export const sampleWithFullData: ICatalogs = {
  id: '3f257660-a614-41b9-950a-b76c9b486f80',
  externalId: '../fake-data/blob/hipster.txt',
  name: '../fake-data/blob/hipster.txt',
  version: '../fake-data/blob/hipster.txt',
  isActive: false,
  createdTime: dayjs('2023-07-17T18:29'),
};

export const sampleWithNewData: NewCatalogs = {
  externalId: '../fake-data/blob/hipster.txt',
  name: '../fake-data/blob/hipster.txt',
  isActive: false,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
