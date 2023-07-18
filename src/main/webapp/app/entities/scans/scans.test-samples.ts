import dayjs from 'dayjs/esm';

import { IScans, NewScans } from './scans.model';

export const sampleWithRequiredData: IScans = {
  id: 'a16cba86-bab1-4b53-9dd0-41f096029642',
  scannerId: '../fake-data/blob/hipster.txt',
  sequenceInBatch: 20599,
  state: 'Diesel bleakly',
  dE: 18050,
  createdTime: dayjs('2023-07-17T17:54'),
};

export const sampleWithPartialData: IScans = {
  id: 'c05428cd-1b53-4847-87f3-4a9a708f053c',
  scannerId: '../fake-data/blob/hipster.txt',
  sequenceInBatch: 13379,
  state: 'Bronze bandwidth',
  dE: 31870,
  createdTime: dayjs('2023-07-18T04:58'),
  scannedTime: dayjs('2023-07-17T16:09'),
  inspectedTime: dayjs('2023-07-17T14:09'),
  modifiedTime: dayjs('2023-07-17T18:11'),
  ejectedTime: dayjs('2023-07-17T11:54'),
};

export const sampleWithFullData: IScans = {
  id: '5e1d45a6-a78b-40e4-ae5f-9a3879eb74f4',
  scannerId: '../fake-data/blob/hipster.txt',
  sequenceInBatch: 25106,
  state: 'connecting Alaba',
  dE: 2674,
  createdTime: dayjs('2023-07-17T15:21'),
  scannedTime: dayjs('2023-07-17T17:53'),
  inspectedTime: dayjs('2023-07-18T00:32'),
  modifiedTime: dayjs('2023-07-18T00:41'),
  ejectedTime: dayjs('2023-07-17T20:50'),
};

export const sampleWithNewData: NewScans = {
  scannerId: '../fake-data/blob/hipster.txt',
  sequenceInBatch: 1495,
  state: 'parsing Bespoke',
  dE: 32745,
  createdTime: dayjs('2023-07-18T01:52'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
