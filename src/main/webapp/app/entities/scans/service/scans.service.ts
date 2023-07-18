import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IScans, NewScans } from '../scans.model';

export type PartialUpdateScans = Partial<IScans> & Pick<IScans, 'id'>;

type RestOf<T extends IScans | NewScans> = Omit<T, 'createdTime' | 'scannedTime' | 'inspectedTime' | 'modifiedTime' | 'ejectedTime'> & {
  createdTime?: string | null;
  scannedTime?: string | null;
  inspectedTime?: string | null;
  modifiedTime?: string | null;
  ejectedTime?: string | null;
};

export type RestScans = RestOf<IScans>;

export type NewRestScans = RestOf<NewScans>;

export type PartialUpdateRestScans = RestOf<PartialUpdateScans>;

export type EntityResponseType = HttpResponse<IScans>;
export type EntityArrayResponseType = HttpResponse<IScans[]>;

@Injectable({ providedIn: 'root' })
export class ScansService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/scans');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(scans: NewScans): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(scans);
    return this.http.post<RestScans>(this.resourceUrl, copy, { observe: 'response' }).pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(scans: IScans): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(scans);
    return this.http
      .put<RestScans>(`${this.resourceUrl}/${this.getScansIdentifier(scans)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(scans: PartialUpdateScans): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(scans);
    return this.http
      .patch<RestScans>(`${this.resourceUrl}/${this.getScansIdentifier(scans)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestScans>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestScans[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getScansIdentifier(scans: Pick<IScans, 'id'>): string {
    return scans.id;
  }

  compareScans(o1: Pick<IScans, 'id'> | null, o2: Pick<IScans, 'id'> | null): boolean {
    return o1 && o2 ? this.getScansIdentifier(o1) === this.getScansIdentifier(o2) : o1 === o2;
  }

  addScansToCollectionIfMissing<Type extends Pick<IScans, 'id'>>(
    scansCollection: Type[],
    ...scansToCheck: (Type | null | undefined)[]
  ): Type[] {
    const scans: Type[] = scansToCheck.filter(isPresent);
    if (scans.length > 0) {
      const scansCollectionIdentifiers = scansCollection.map(scansItem => this.getScansIdentifier(scansItem)!);
      const scansToAdd = scans.filter(scansItem => {
        const scansIdentifier = this.getScansIdentifier(scansItem);
        if (scansCollectionIdentifiers.includes(scansIdentifier)) {
          return false;
        }
        scansCollectionIdentifiers.push(scansIdentifier);
        return true;
      });
      return [...scansToAdd, ...scansCollection];
    }
    return scansCollection;
  }

  protected convertDateFromClient<T extends IScans | NewScans | PartialUpdateScans>(scans: T): RestOf<T> {
    return {
      ...scans,
      createdTime: scans.createdTime?.toJSON() ?? null,
      scannedTime: scans.scannedTime?.toJSON() ?? null,
      inspectedTime: scans.inspectedTime?.toJSON() ?? null,
      modifiedTime: scans.modifiedTime?.toJSON() ?? null,
      ejectedTime: scans.ejectedTime?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restScans: RestScans): IScans {
    return {
      ...restScans,
      createdTime: restScans.createdTime ? dayjs(restScans.createdTime) : undefined,
      scannedTime: restScans.scannedTime ? dayjs(restScans.scannedTime) : undefined,
      inspectedTime: restScans.inspectedTime ? dayjs(restScans.inspectedTime) : undefined,
      modifiedTime: restScans.modifiedTime ? dayjs(restScans.modifiedTime) : undefined,
      ejectedTime: restScans.ejectedTime ? dayjs(restScans.ejectedTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestScans>): HttpResponse<IScans> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestScans[]>): HttpResponse<IScans[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
