import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBatches, NewBatches } from '../batches.model';

export type PartialUpdateBatches = Partial<IBatches> & Pick<IBatches, 'id'>;

type RestOf<T extends IBatches | NewBatches> = Omit<
  T,
  'catalogCreatedtime' | 'orderedTime' | 'startedTime' | 'modifiedTime' | 'suspendedTime' | 'finishedTime'
> & {
  catalogCreatedtime?: string | null;
  orderedTime?: string | null;
  startedTime?: string | null;
  modifiedTime?: string | null;
  suspendedTime?: string | null;
  finishedTime?: string | null;
};

export type RestBatches = RestOf<IBatches>;

export type NewRestBatches = RestOf<NewBatches>;

export type PartialUpdateRestBatches = RestOf<PartialUpdateBatches>;

export type EntityResponseType = HttpResponse<IBatches>;
export type EntityArrayResponseType = HttpResponse<IBatches[]>;

@Injectable({ providedIn: 'root' })
export class BatchesService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/batches');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(batches: NewBatches): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(batches);
    return this.http
      .post<RestBatches>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(batches: IBatches): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(batches);
    return this.http
      .put<RestBatches>(`${this.resourceUrl}/${this.getBatchesIdentifier(batches)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(batches: PartialUpdateBatches): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(batches);
    return this.http
      .patch<RestBatches>(`${this.resourceUrl}/${this.getBatchesIdentifier(batches)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestBatches>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestBatches[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getBatchesIdentifier(batches: Pick<IBatches, 'id'>): string {
    return batches.id;
  }

  compareBatches(o1: Pick<IBatches, 'id'> | null, o2: Pick<IBatches, 'id'> | null): boolean {
    return o1 && o2 ? this.getBatchesIdentifier(o1) === this.getBatchesIdentifier(o2) : o1 === o2;
  }

  addBatchesToCollectionIfMissing<Type extends Pick<IBatches, 'id'>>(
    batchesCollection: Type[],
    ...batchesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const batches: Type[] = batchesToCheck.filter(isPresent);
    if (batches.length > 0) {
      const batchesCollectionIdentifiers = batchesCollection.map(batchesItem => this.getBatchesIdentifier(batchesItem)!);
      const batchesToAdd = batches.filter(batchesItem => {
        const batchesIdentifier = this.getBatchesIdentifier(batchesItem);
        if (batchesCollectionIdentifiers.includes(batchesIdentifier)) {
          return false;
        }
        batchesCollectionIdentifiers.push(batchesIdentifier);
        return true;
      });
      return [...batchesToAdd, ...batchesCollection];
    }
    return batchesCollection;
  }

  protected convertDateFromClient<T extends IBatches | NewBatches | PartialUpdateBatches>(batches: T): RestOf<T> {
    return {
      ...batches,
      catalogCreatedtime: batches.catalogCreatedtime?.toJSON() ?? null,
      orderedTime: batches.orderedTime?.toJSON() ?? null,
      startedTime: batches.startedTime?.toJSON() ?? null,
      modifiedTime: batches.modifiedTime?.toJSON() ?? null,
      suspendedTime: batches.suspendedTime?.toJSON() ?? null,
      finishedTime: batches.finishedTime?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restBatches: RestBatches): IBatches {
    return {
      ...restBatches,
      catalogCreatedtime: restBatches.catalogCreatedtime ? dayjs(restBatches.catalogCreatedtime) : undefined,
      orderedTime: restBatches.orderedTime ? dayjs(restBatches.orderedTime) : undefined,
      startedTime: restBatches.startedTime ? dayjs(restBatches.startedTime) : undefined,
      modifiedTime: restBatches.modifiedTime ? dayjs(restBatches.modifiedTime) : undefined,
      suspendedTime: restBatches.suspendedTime ? dayjs(restBatches.suspendedTime) : undefined,
      finishedTime: restBatches.finishedTime ? dayjs(restBatches.finishedTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestBatches>): HttpResponse<IBatches> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestBatches[]>): HttpResponse<IBatches[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
