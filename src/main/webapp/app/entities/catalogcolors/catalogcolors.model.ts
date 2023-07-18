import { ICatalogs } from 'app/entities/catalogs/catalogs.model';

export interface ICatalogcolors {
  id: string;
  code?: string | null;
  name?: string | null;
  baseMaterial?: number | null;
  labL?: number | null;
  labA?: number | null;
  labB?: number | null;
  a?: number | null;
  b?: number | null;
  c?: number | null;
  d?: number | null;
  e?: number | null;
  f?: number | null;
  g?: number | null;
  h?: number | null;
  i?: number | null;
  j?: number | null;
  k?: number | null;
  l?: number | null;
  m?: number | null;
  n?: number | null;
  o?: number | null;
  p?: number | null;
  q?: number | null;
  r?: number | null;
  s?: number | null;
  t?: number | null;
  u?: number | null;
  v?: number | null;
  w?: number | null;
  x?: number | null;
  y?: number | null;
  z?: number | null;
  catalog?: Pick<ICatalogs, 'id'> | null;
}

export type NewCatalogcolors = Omit<ICatalogcolors, 'id'> & { id: null };
