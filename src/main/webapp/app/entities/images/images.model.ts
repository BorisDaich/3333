export interface IImages {
  id: string;
  pngContent?: string | null;
  pngContentContentType?: string | null;
  rawWidth?: number | null;
  rawHeight?: number | null;
  rawFormat?: string | null;
  rawContent?: string | null;
  rawContentContentType?: string | null;
}

export type NewImages = Omit<IImages, 'id'> & { id: null };
