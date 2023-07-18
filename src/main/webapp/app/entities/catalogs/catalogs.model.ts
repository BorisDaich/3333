import dayjs from 'dayjs/esm';

export interface ICatalogs {
  id: string;
  externalId?: string | null;
  name?: string | null;
  version?: string | null;
  isActive?: boolean | null;
  createdTime?: dayjs.Dayjs | null;
}

export type NewCatalogs = Omit<ICatalogs, 'id'> & { id: null };
