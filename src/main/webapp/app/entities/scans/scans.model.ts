import dayjs from 'dayjs/esm';
import { IImages } from 'app/entities/images/images.model';
import { IBatches } from 'app/entities/batches/batches.model';

export interface IScans {
  id: string;
  scannerId?: string | null;
  sequenceInBatch?: number | null;
  state?: string | null;
  dE?: number | null;
  createdTime?: dayjs.Dayjs | null;
  scannedTime?: dayjs.Dayjs | null;
  inspectedTime?: dayjs.Dayjs | null;
  modifiedTime?: dayjs.Dayjs | null;
  ejectedTime?: dayjs.Dayjs | null;
  image?: Pick<IImages, 'id'> | null;
  productionBatch?: Pick<IBatches, 'id'> | null;
}

export type NewScans = Omit<IScans, 'id'> & { id: null };
